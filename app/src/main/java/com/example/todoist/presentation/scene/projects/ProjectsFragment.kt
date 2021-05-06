package com.example.todoist.presentation.scene.projects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoist.R
import com.example.todoist.common.TodoistApplication
import com.example.todoist.databinding.FragmentProjectsBinding
import com.example.todoist.presentation.common.ScreenState
import com.example.todoist.presentation.scene.main.MainActivity
import com.example.todoist.presentation.scene.main.MainViewModel
import com.example.todoist.presentation.scene.sections.SectionsFragment
import javax.inject.Inject

class ProjectsFragment : Fragment() {

    @Inject
    lateinit var  viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding:FragmentProjectsBinding
    private lateinit var viewModel:ProjectsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as TodoistApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProjectsAdapter()

        binding.projectList.adapter = adapter
        binding.projectList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.navigationSections.observe(this) { projectIdEvent ->
            projectIdEvent.handleEvent { projectId ->

                val fragment = SectionsFragment.newInstance(projectId)

                requireFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        viewModel.screenState.observe(this){screenState ->
            when(screenState){
                is ScreenState.Success -> {
                    adapter.setItems(screenState.data){ project ->
                        viewModel.onProjectClicked(project)
                    }
                    binding.emptyStateIndicator.visibility = View.GONE
                    binding.progressIndicator.visibility = View.GONE
                    binding.projectList.visibility = View.VISIBLE

                }
                is ScreenState.Loading -> {
                    binding.emptyStateIndicator.visibility = View.GONE
                    binding.progressIndicator.visibility = View.VISIBLE
                    binding.projectList.visibility = View.GONE
                }
                is ScreenState.Error -> {
                    binding.emptyStateIndicator.visibility = View.VISIBLE
                    binding.progressIndicator.visibility = View.GONE
                    binding.projectList.visibility = View.GONE
                }
            }
        }

    }
}