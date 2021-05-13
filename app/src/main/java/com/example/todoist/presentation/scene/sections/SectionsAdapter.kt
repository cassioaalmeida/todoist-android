package com.example.todoist.presentation.scene.sections

import android.view.View
import com.example.domain.model.Section
import com.example.todoist.R
import com.example.todoist.databinding.SimpleTextItemBinding
import com.example.todoist.presentation.scene.projects.ProjectDM
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class SectionsAdapter(
    private val onItemClicked: (SectionDM) -> Unit
): GroupAdapter<GroupieViewHolder>() {
    fun setItems(sectionsList: List<SectionDM>){
        sectionsList.forEach { add(SectionItem(it)) }
    }

    inner class  SectionItem(private val section: SectionDM): BindableItem<SimpleTextItemBinding>() {
        override fun bind(viewBinding: SimpleTextItemBinding, position: Int) {
            viewBinding.textItem.text = section.name

            viewBinding.root.setOnClickListener {
                onItemClicked(section)
            }
        }

        override fun getLayout(): Int = R.layout.simple_text_item

        override fun initializeViewBinding(view: View): SimpleTextItemBinding {
            return SimpleTextItemBinding.bind(view)
        }
    }
}