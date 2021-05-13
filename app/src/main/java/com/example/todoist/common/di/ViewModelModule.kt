package com.example.todoist.common.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoist.presentation.common.ViewModelFactory
import com.example.todoist.presentation.scene.main.MainViewModel
import com.example.todoist.presentation.scene.projects.ProjectsViewModel
import com.example.todoist.presentation.scene.sections.SectionsViewModel
import com.example.todoist.presentation.scene.tasks.TasksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProjectsViewModel::class)
    internal abstract fun projectsViewModel(viewModel: ProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SectionsViewModel::class)
    internal abstract fun sectionsViewModel(viewModel: SectionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    internal abstract fun tasksViewModel(viewModel: TasksViewModel): ViewModel

    //Add more ViewModels here
}