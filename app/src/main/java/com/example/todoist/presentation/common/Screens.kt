package com.example.todoist.presentation.common

import com.example.todoist.presentation.scene.projects.ProjectsFragment
import com.example.todoist.presentation.scene.sections.SectionsFragment
import com.example.todoist.presentation.scene.tasks.TasksFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun projectsScreen() = FragmentScreen {
        ProjectsFragment.newInstance()
    }
    fun SectionsScreen(projectId:Long) = FragmentScreen {
        SectionsFragment.newInstance(projectId)
    }
    fun TasksScreen(sectionId:Long) = FragmentScreen {
        TasksFragment.newInstance(sectionId)
    }
}