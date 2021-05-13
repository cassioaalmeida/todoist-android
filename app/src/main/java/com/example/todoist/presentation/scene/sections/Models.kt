package com.example.todoist.presentation.scene.sections

import android.graphics.Color
import com.example.domain.model.Project
import com.example.domain.model.Section

data class SectionDM (
    val id: Long,
    val name: String
)

fun Section.toDisplayModel() = SectionDM(id, name)