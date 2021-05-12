package com.example.todoist.presentation.scene.projects

import android.view.View
import com.example.domain.model.Project
import com.example.todoist.R
import com.example.todoist.databinding.SimpleTextItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class ProjectsAdapter: GroupAdapter<GroupieViewHolder>() {

    fun setItems(projectList: List<Project>, clickListener: (Project) -> Unit){
        projectList.forEach { add(ProjectItem(it, clickListener)) }
    }

    internal class  ProjectItem(private val project: Project, val clickListener: (Project) -> Unit): BindableItem<SimpleTextItemBinding>() {
        override fun bind(viewBinding: SimpleTextItemBinding, position: Int) {
            viewBinding.textItem.text = project.name

            viewBinding.root.setOnClickListener {
                clickListener(project)
            }
        }

        override fun getLayout(): Int = R.layout.simple_text_item

        override fun initializeViewBinding(view: View): SimpleTextItemBinding {
            return SimpleTextItemBinding.bind(view)
        }
    }

}