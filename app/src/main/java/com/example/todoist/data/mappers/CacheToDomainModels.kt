package com.example.todoist.data.mappers

import com.example.domain.model.Color
import com.example.domain.model.Project
import com.example.domain.model.Section
import com.example.domain.model.Task
import com.example.todoist.data.cache.model.ColorCM
import com.example.todoist.data.cache.model.ProjectCM
import com.example.todoist.data.cache.model.SectionCM
import com.example.todoist.data.cache.model.TaskCM

fun ProjectCM.toDomainModel() = Project(id, color.toDomainModel(), name)

fun ColorCM.toDomainModel() = Color(name, hex)

fun SectionCM.toDomainModel() = Section(id, name)

fun TaskCM.toDomainModel() = Task(id, content, completed, created)