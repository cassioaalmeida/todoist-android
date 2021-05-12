package com.example.todoist.data.repository

import com.example.domain.datarepository.TodoistDataRepository
import com.example.domain.model.Project
import com.example.todoist.common.di.IOScheduler
import com.example.todoist.common.di.MainScheduler
import com.example.todoist.data.cache.TodoistCDS
import com.example.todoist.data.mappers.toCacheModel
import com.example.todoist.data.mappers.toDomainModel
import com.example.todoist.data.model.Section
import com.example.todoist.data.model.Task
import com.example.todoist.data.model.UpsertTaskBody
import com.example.todoist.data.remote.TodoistRDS
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class TodoistRepository @Inject constructor(
    private val todoistCDS: TodoistCDS,
    private val todoistRDS: TodoistRDS,
    @MainScheduler private val mainScheduler: Scheduler,
    @IOScheduler private val ioScheduler: Scheduler
): TodoistDataRepository {

    override fun getProjects(): Single<List<Project>> =
        todoistRDS.getProjects()
            .map { it.map { it.toCacheModel() } }
            .flatMap {
                todoistCDS.upsertProjects(it)
                    .toSingleDefault(it)
            }.onErrorResumeNext {
                todoistCDS.getProjects()
            }
            .map { it.map { it.toDomainModel() } }
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)

    fun getSections(projectId: Long): Single<List<Section>> =
        todoistRDS.getSections(projectId)
            .flatMap {
                todoistCDS.upsertSections(projectId, it)
                Single.just(it)
            }.onErrorResumeNext {
                todoistCDS.getSections(projectId)
            }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)

    fun getTasks(sectionId: Long): Single<List<Task>> =
        todoistRDS.getTasks(sectionId)
            .flatMap {
                todoistCDS.upsertTasks(sectionId, it)
                Single.just(it)
            }.onErrorResumeNext {
                todoistCDS.getTasks(sectionId)
            }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)

    fun addTask(taskBody: UpsertTaskBody): Completable =
        todoistRDS.addTask(taskBody)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
}