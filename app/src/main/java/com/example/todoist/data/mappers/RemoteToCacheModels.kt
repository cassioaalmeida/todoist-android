package com.example.todoist.data.mappers

import com.example.domain.model.Project
import com.example.todoist.data.cache.model.ColorCM
import com.example.todoist.data.cache.model.ProjectCM
import com.example.todoist.data.cache.model.SectionCM
import com.example.todoist.data.cache.model.TaskCM
import com.example.todoist.data.remote.model.ProjectRM
import com.example.todoist.data.remote.model.SectionRM
import com.example.todoist.data.remote.model.TaskRM

fun ProjectRM.toCacheModel() = ProjectCM (id, getColorCMFromId(colorId), name)

fun getColorCMFromId(colorId: Int): ColorCM {
    return when(colorId) {
        30 -> ColorCM ("berry_red", "#b8256f")
        else -> ColorCM ("white", "#FFFFFF")
    }
}

fun SectionRM.toCacheModel() = SectionCM(id, name)

fun TaskRM.toCacheModel() = TaskCM(id, content, completed, created)