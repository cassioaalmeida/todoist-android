package com.example.todoist.presentation.scene.sections

import android.view.View
import com.example.todoist.R
import com.example.todoist.data.model.Project
import com.example.todoist.data.model.Section
import com.example.todoist.databinding.SimpleTextItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class SectionsAdapter: GroupAdapter<GroupieViewHolder>() {
    fun setItems(sectionsList: List<Section>, clickListener: (Section) -> Unit){
        sectionsList.forEach { add(SectionItem(it, clickListener)) }
    }

    internal class  SectionItem(private val section: Section, val clickListener: (Section) -> Unit): BindableItem<SimpleTextItemBinding>() {
        override fun bind(viewBinding: SimpleTextItemBinding, position: Int) {
            viewBinding.textItem.text = section.name

            viewBinding.root.setOnClickListener {
                clickListener(section)
            }
        }

        override fun getLayout(): Int = R.layout.simple_text_item

        override fun initializeViewBinding(view: View): SimpleTextItemBinding {
            return SimpleTextItemBinding.bind(view)
        }
    }
}