package com.example.domain.model

data class Task(
    val id: Long,
    val content: String,
    val completed: Boolean,
    val created: String
)
