package com.example.todoist.presentation.scene.projects

import androidx.lifecycle.ViewModel
import com.example.todoist.data.repository.TodoistRepository
import javax.inject.Inject

class ProjectsViewModel @Inject constructor(
    private val repository: TodoistRepository
): ViewModel() {
}