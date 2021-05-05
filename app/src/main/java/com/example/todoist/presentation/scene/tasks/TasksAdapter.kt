package com.example.todoist.presentation.scene.tasks

import android.view.View
import com.example.todoist.R
import com.example.todoist.data.model.Task
import com.example.todoist.databinding.TaskItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class TasksAdapter: GroupAdapter<GroupieViewHolder>() {
    fun setItems(tasksList: List<Task>){
        tasksList.forEach { add(TaskItem(it)) }
    }

    internal class  TaskItem(private val task: Task): BindableItem<TaskItemBinding>() {
        override fun bind(viewBinding: TaskItemBinding, position: Int) {
            viewBinding.contentItem.text = task.content
            viewBinding.dateItem.text = task.created
            viewBinding.checkboxComplete.isChecked = task.completed
        }

        override fun getLayout(): Int = R.layout.task_item

        override fun initializeViewBinding(view: View): TaskItemBinding {
            return TaskItemBinding.bind(view)
        }
    }
}