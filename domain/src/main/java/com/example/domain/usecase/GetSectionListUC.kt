package com.example.domain.usecase

import com.example.domain.datarepository.TodoistDataRepository
import com.example.domain.model.Section
import io.reactivex.Single
import javax.inject.Inject

class GetSectionListUC @Inject constructor(
    private val todoistDataRepository: TodoistDataRepository
)
{

    fun getSingle(projectId: Long) : Single<List<Section>> =
        todoistDataRepository.getSections(projectId)
}