package com.example.todoist.presentation.scene.projects

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todoist.R
import com.example.todoist.common.TodoistApplication
import com.example.todoist.databinding.FragmentProjectsBinding
import com.example.todoist.presentation.scene.main.MainActivity
import com.example.todoist.presentation.scene.main.MainViewModel
import javax.inject.Inject

class ProjectsFragment : Fragment() {

    @Inject
    lateinit var  viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding:FragmentProjectsBinding
    private lateinit var viewModel:ProjectsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as TodoistApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this).get(ProjectsViewModel::class.java)
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


    }
}