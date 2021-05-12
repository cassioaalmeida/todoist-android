package com.example.todoist.data.mappers

import com.example.domain.model.Color
import com.example.domain.model.Project
import com.example.todoist.data.cache.model.ColorCM
import com.example.todoist.data.cache.model.ProjectCM

fun ProjectCM.toDomainModel() = Project(id, color.toDomainModel(), name)

fun ColorCM.toDomainModel() = Color(name, hex)