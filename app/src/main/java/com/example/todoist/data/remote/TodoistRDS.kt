package com.example.todoist.data.remote

import com.example.todoist.data.model.Section
import com.example.todoist.data.model.Task
import com.example.todoist.data.model.UpsertTaskBody
import com.example.todoist.data.remote.model.ProjectRM
import com.example.todoist.data.remote.model.SectionRM
import com.example.todoist.data.remote.model.TaskRM
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface TodoistRDS {

    @GET("projects")
    fun getProjects(): Single<List<ProjectRM>>

    @GET("sections")
    fun getSections(@Query("project_id") projectId: Long): Single<List<SectionRM>>

    @GET("tasks")
    fun getTasks(@Query("section_id") sectionId: Long): Single<List<TaskRM>>

    @GET("tasks/{id}")
    fun getTask(@Path("id") taskId: Long): Single<TaskRM>

    @POST("tasks")
    fun addTask(@Body taskBody: UpsertTaskBody): Completable

}