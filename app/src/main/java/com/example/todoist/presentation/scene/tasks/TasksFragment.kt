package com.example.todoist.presentation.scene.tasks

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoist.R
import com.example.todoist.common.TodoistApplication
import com.example.todoist.databinding.FragmentTasksBinding
import com.example.todoist.presentation.common.ScreenState
import com.example.todoist.presentation.scene.sections.SectionsFragment
import com.example.todoist.presentation.scene.tasks.TasksAdapter
import com.example.todoist.presentation.scene.tasks.TasksViewModel
import javax.inject.Inject

class TasksFragment : Fragment() {

    companion object {
        const val TASKS_KEY = "TASKS_KEY"

        fun newInstance(sectionId: Long): TasksFragment {
            val tasksFragment = TasksFragment()

            val bundle = Bundle()

            bundle.putLong(TASKS_KEY, sectionId)

            tasksFragment.arguments = bundle

            return tasksFragment
        }
    }

    @Inject
    lateinit var  viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentTasksBinding
    private lateinit var viewModel: TasksViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as TodoistApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TasksViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TasksAdapter()

        binding.taskList.adapter = adapter
        binding.taskList.layoutManager = LinearLayoutManager(requireContext())

        val receivedSectionId = this.arguments?.get(TASKS_KEY)

        viewModel.onIdReceived(receivedSectionId.toString().toLong())

        viewModel.screenState.observe(this){screenState ->
            when(screenState){
                is ScreenState.Success -> {
                    adapter.setItems(screenState.data)
                    binding.emptyStateIndicator.visibility = View.GONE
                    binding.progressIndicator.visibility = View.GONE
                    binding.taskList.visibility = View.VISIBLE

                }
                is ScreenState.Loading -> {
                    binding.emptyStateIndicator.visibility = View.GONE
                    binding.progressIndicator.visibility = View.VISIBLE
                    binding.taskList.visibility = View.GONE
                }
                is ScreenState.Error -> {
                    binding.emptyStateIndicator.visibility = View.VISIBLE
                    binding.progressIndicator.visibility = View.GONE
                    binding.taskList.visibility = View.GONE
                }
            }
        }

    }

}