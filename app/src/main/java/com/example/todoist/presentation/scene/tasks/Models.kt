package com.example.todoist.presentation.scene.tasks

import android.graphics.Color
import com.example.domain.model.Project
import com.example.domain.model.Section
import com.example.domain.model.Task

data class TaskDM (
    val id: Long,
    val content: String,
    val completed: Boolean,
    val created: String
)

fun Task.toDisplayModel() = TaskDM(id, content, completed, created)