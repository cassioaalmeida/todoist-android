package com.example.todoist.presentation.common

sealed class ScreenState<out T>{
    class Success<out T>(val data: T): ScreenState<T>()
    object Error : ScreenState<Nothing>()
    object Loading : ScreenState<Nothing>()
}