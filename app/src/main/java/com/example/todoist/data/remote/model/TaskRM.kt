package com.example.todoist.data.remote.model

data class TaskRM(
    val id: Long,
    val content: String,
    val completed: Boolean,
    val created: String
)
