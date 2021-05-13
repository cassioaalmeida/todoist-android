package com.example.todoist.presentation.scene.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Project
import com.example.domain.usecase.GetProjectListUC
import com.example.todoist.presentation.common.ScreenState
import com.example.todoist.presentation.common.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ProjectsViewModel @Inject constructor(
    private val getProjectListUC: GetProjectListUC,
    private val compositeDisposable: CompositeDisposable,
    private val router: Router
): ViewModel() {

    private val _screenState: MutableLiveData<ScreenState<List<ProjectDM>>> = MutableLiveData()
    val screenState: LiveData<ScreenState<List<ProjectDM>>>
        get() = _screenState

    init {
        getProjectListUC.getSingle()
            .doOnSubscribe { _screenState.value = ScreenState.Loading }
            .map { it.map { it.toDisplayModel() } }
            .subscribe(
                { _screenState.value = ScreenState.Success(it) },
                {_screenState.value = ScreenState.Error}
            ).addTo(compositeDisposable)
    }

    fun onProjectClicked(project: ProjectDM) {
        router.navigateTo(Screens.SectionsScreen(project.id))
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}