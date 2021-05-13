package com.example.todoist.presentation.scene.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetProjectListUC
import com.example.domain.usecase.GetSectionListUC
import com.example.todoist.data.repository.TodoistRepository
import com.example.todoist.presentation.common.ScreenState
import com.example.todoist.presentation.common.Screens
import com.example.todoist.presentation.scene.projects.toDisplayModel
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SectionsViewModel @Inject constructor(
    private val getSectionListUC: GetSectionListUC,
    private val repository: TodoistRepository,
    private val compositeDisposable: CompositeDisposable,
    private val router: Router
): ViewModel() {
    private val _screenState: MutableLiveData<ScreenState<List<SectionDM>>> = MutableLiveData()
    val screenState: LiveData<ScreenState<List<SectionDM>>>
        get() = _screenState

    fun onIdReceived(projectId: Long) {
        getSectionListUC.getSingle(projectId)
            .doOnSubscribe { _screenState.value = ScreenState.Loading }
            .map { it.map { it.toDisplayModel() } }
            .subscribe(
                { _screenState.value = ScreenState.Success(it) },
                {_screenState.value = ScreenState.Error}
            ).addTo(compositeDisposable)
    }

    fun onSectionClicked(section: SectionDM) {
        router.navigateTo(Screens.TasksScreen(section.id))
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}