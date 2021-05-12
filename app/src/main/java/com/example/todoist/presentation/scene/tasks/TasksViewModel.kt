package com.example.todoist.presentation.scene.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Task
import com.example.domain.usecase.GetTaskListUC
import com.example.todoist.data.repository.TodoistRepository
import com.example.todoist.presentation.common.ScreenState
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val getTaskListUC: GetTaskListUC,
    private val repository: TodoistRepository,
    private val compositeDisposable: CompositeDisposable
): ViewModel() {
    private val _screenState: MutableLiveData<ScreenState<List<Task>>> = MutableLiveData()
    val screenState: LiveData<ScreenState<List<Task>>>
        get() = _screenState


    fun onIdReceived(sectionId: Long) {
        getTaskListUC.getSingle(sectionId)
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