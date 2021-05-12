package com.example.todoist.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProjectRM(
    val id: Long,
    @SerializedName("color")
    val colorId: Int,
    val name: String
)
