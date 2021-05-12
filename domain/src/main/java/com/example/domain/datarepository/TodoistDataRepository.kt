package com.example.domain.datarepository

import com.example.domain.model.Project
import com.example.domain.model.Section
import com.example.domain.model.Task
import io.reactivex.Single

interface TodoistDataRepository {
    fun getProjects(): Single<List<Project>>
    fun getSections(projectId: Long): Single<List<Section>>
    fun getTasks(sectionId: Long): Single<List<Task>>
}