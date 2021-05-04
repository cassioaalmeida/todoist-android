package com.example.todoist.presentation.scene.projects

import android.view.View
import com.example.todoist.R
import com.example.todoist.data.model.Project
import com.example.todoist.databinding.SimpleTextItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class ProjectsAdapter: GroupAdapter<GroupieViewHolder>() {

    fun setItems(projectList: List<Project>){
        projectList.forEach { add(ProjectItem(it)) }
    }

    internal class  ProjectItem(private val project: Project): BindableItem<SimpleTextItemBinding>() {
        override fun bind(viewBinding: SimpleTextItemBinding, position: Int) {
            viewBinding.textItem.text = project.name
        }

        override fun getLayout(): Int = R.layout.simple_text_item

        override fun initializeViewBinding(view: View): SimpleTextItemBinding {
            return SimpleTextItemBinding.bind(view)
        }
    }

}