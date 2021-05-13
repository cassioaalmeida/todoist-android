package com.example.domain.usecase

import com.example.domain.datarepository.TodoistDataRepository
import com.example.domain.model.Project
import io.reactivex.Single
import javax.inject.Inject

class GetProjectListUC @Inject constructor (
    private val todoistDataRepository: TodoistDataRepository
    )
{

    fun getSingle() : Single<List<Project>> =
        todoistDataRepository.getProjects()
}