package com.example.todoist.presentation.scene.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoist.data.model.Project
import com.example.todoist.data.repository.TodoistRepository
import com.example.todoist.presentation.common.Event
import com.example.todoist.presentation.common.ScreenState
import com.example.todoist.presentation.common.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ProjectsViewModel @Inject constructor(
    private val repository: TodoistRepository,
    private val compositeDisposable: CompositeDisposable,
    private val router: Router
): ViewModel() {

    private val _screenState: MutableLiveData<ScreenState<List<Project>>> = MutableLiveData()
    val screenState: LiveData<ScreenState<List<Project>>>
        get() = _screenState

    init {
        repository.getProjects()
            .doOnSubscribe { _screenState.value = ScreenState.Loading }
            .subscribe(
                { _screenState.value = ScreenState.Success(it) },
                {_screenState.value = ScreenState.Error}
            ).addTo(compositeDisposable)
    }

    fun onProjectClicked(project: Project) {
        router.navigateTo(Screens.SectionsScreen(project.id))
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}