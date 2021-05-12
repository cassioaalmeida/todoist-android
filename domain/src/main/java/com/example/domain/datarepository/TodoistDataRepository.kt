package com.example.domain.datarepository

import com.example.domain.model.Project
import io.reactivex.Single

interface TodoistDataRepository {
    fun getProjects(): Single<List<Project>>
}