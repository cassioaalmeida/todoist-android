package com.example.todoist.presentation.scene.sections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoist.data.model.Project
import com.example.todoist.data.model.Section
import com.example.todoist.data.repository.TodoistRepository
import com.example.todoist.presentation.common.Event
import com.example.todoist.presentation.common.ScreenState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SectionsViewModel @Inject constructor(
    private val repository: TodoistRepository,
    private val compositeDisposable: CompositeDisposable
): ViewModel() {
    private val _screenState: MutableLiveData<ScreenState<List<Section>>> = MutableLiveData()
    val screenState: LiveData<ScreenState<List<Section>>>
        get() = _screenState


    private val _navigationTasks: MutableLiveData<Event<Long>> = MutableLiveData()
    val navigationTasks: MutableLiveData<Event<Long>>
        get() = _navigationTasks

    fun onIdReceived(projectId: Long) {
        repository.getSections(projectId)
            .doOnSubscribe { _screenState.value = ScreenState.Loading }
            .subscribe(
                { _screenState.value = ScreenState.Success(it) },
                {_screenState.value = ScreenState.Error}
            ).addTo(compositeDisposable)
    }

    fun onSectionClicked(section: Section) {
        navigationTasks.value = Event(section.id!!)
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}