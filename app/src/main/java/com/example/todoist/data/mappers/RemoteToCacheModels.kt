package com.example.todoist.data.mappers

import com.example.domain.model.Project
import com.example.todoist.data.cache.model.ColorCM
import com.example.todoist.data.cache.model.ProjectCM
import com.example.todoist.data.remote.model.ProjectRM

fun ProjectRM.toCacheModel() = ProjectCM (id, getColorCMFromId(colorId), name)

fun getColorCMFromId(colorId: Int): ColorCM {
    return when(colorId) {
        30 -> ColorCM ("berry_red", "#b8256f")
        else -> ColorCM ("white", "#FFFFFF")
    }
}