package com.example.todoist.presentation.scene.sections

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoist.R
import com.example.todoist.common.TodoistApplication
import com.example.todoist.databinding.FragmentSectionsBinding
import com.example.todoist.presentation.common.ScreenState
import com.example.todoist.presentation.scene.tasks.TasksFragment
import javax.inject.Inject


class SectionsFragment : Fragment() {

    companion object {
        const val SECTIONS_KEY = "SECTIONS_KEY"
    }

    @Inject
    lateinit var  viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentSectionsBinding
    private lateinit var viewModel: SectionsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as TodoistApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SectionsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSectionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SectionsAdapter()

        binding.sectionList.adapter = adapter
        binding.sectionList.layoutManager = LinearLayoutManager(requireContext())

        val bundle = Bundle()
        val receivedProjectId = bundle.getLong(SECTIONS_KEY, 0)

        viewModel.onIdReceived(receivedProjectId)

        viewModel.navigationTasks.observe(this) { sectionIdEvent ->
            sectionIdEvent.handleEvent { sectionId ->

                val bundle = Bundle()
                val fragment = TasksFragment()
                bundle.putLong(TasksFragment.TASKS_KEY, sectionId);
                fragment.arguments = bundle;

                requireFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, TasksFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        viewModel.screenState.observe(this){ screenState ->
            when(screenState){
                is ScreenState.Success -> {
                    adapter.setItems(screenState.data){ section ->
                        viewModel.onSectionClicked(section)
                    }
                    binding.emptyStateIndicator.visibility = View.GONE
                    binding.progressIndicator.visibility = View.GONE
                    binding.sectionList.visibility = View.VISIBLE

                }
                is ScreenState.Loading -> {
                    binding.emptyStateIndicator.visibility = View.GONE
                    binding.progressIndicator.visibility = View.VISIBLE
                    binding.sectionList.visibility = View.GONE
                }
                is ScreenState.Error -> {
                    binding.emptyStateIndicator.visibility = View.VISIBLE
                    binding.progressIndicator.visibility = View.GONE
                    binding.sectionList.visibility = View.GONE
                }
            }
        }

    }

}