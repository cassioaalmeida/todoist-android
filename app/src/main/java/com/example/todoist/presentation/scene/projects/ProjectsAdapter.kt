package com.example.todoist.presentation.scene.projects

import android.view.View
import com.example.domain.model.Project
import com.example.todoist.R
import com.example.todoist.databinding.SimpleTextItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class ProjectsAdapter(
    private val onItemClicked: (ProjectDM) -> Unit
): GroupAdapter<GroupieViewHolder>() {

    fun setItems(projectList: List<ProjectDM>){
        projectList.forEach { add(ProjectItem(it)) }
    }

    inner class  ProjectItem(private val project: ProjectDM): BindableItem<SimpleTextItemBinding>() {
        override fun bind(viewBinding: SimpleTextItemBinding, position: Int) {
            viewBinding.textItem.text = project.name
            viewBinding.root.setBackgroundColor(project.colorParsed)

            viewBinding.root.setOnClickListener {
                onItemClicked(project)
            }
        }

        override fun getLayout(): Int = R.layout.simple_text_item

        override fun initializeViewBinding(view: View): SimpleTextItemBinding {
            return SimpleTextItemBinding.bind(view)
        }
    }

}