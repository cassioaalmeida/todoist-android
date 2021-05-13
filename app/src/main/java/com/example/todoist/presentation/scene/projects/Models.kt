package com.example.todoist.presentation.scene.projects

import android.graphics.Color
import com.example.domain.model.Project

data class ProjectDM (
    val id: Long,
    val colorParsed: Int,
    val name: String
)

fun Project.toDisplayModel() = ProjectDM(id,  Color.parseColor(color.hex), name)