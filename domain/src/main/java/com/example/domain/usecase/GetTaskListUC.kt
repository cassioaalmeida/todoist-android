package com.example.domain.usecase

import com.example.domain.datarepository.TodoistDataRepository
import com.example.domain.model.Task
import io.reactivex.Single
import javax.inject.Inject

class GetTaskListUC @Inject constructor(
    private val todoistDataRepository: TodoistDataRepository
)
{

    fun getSingle(sectionId: Long) : Single<List<Task>> =
        todoistDataRepository.getTasks(sectionId)
}