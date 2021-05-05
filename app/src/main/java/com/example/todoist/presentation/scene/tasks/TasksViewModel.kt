package com.example.todoist.presentation.scene.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoist.data.model.Task
import com.example.todoist.data.repository.TodoistRepository
import com.example.todoist.presentation.common.ScreenState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val repository: TodoistRepository,
    private val compositeDisposable: CompositeDisposable
): ViewModel() {
    private val _screenState: MutableLiveData<ScreenState<List<Task>>> = MutableLiveData()
    val screenState: LiveData<ScreenState<List<Task>>>
        get() = _screenState


    fun onIdReceived(sectionId: Long) {
        repository.getTasks(sectionId)
            .doOnSubscribe { _screenState.value = ScreenState.Loading }
            .subscribe(
                { _screenState.value = ScreenState.Success(it) },
                {_screenState.value = ScreenState.Error}
            ).addTo(compositeDisposable)
    }


    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }
}