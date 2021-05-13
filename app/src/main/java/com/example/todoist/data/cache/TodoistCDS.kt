package com.example.todoist.data.cache

import com.example.todoist.data.cache.model.ProjectCM
import com.example.todoist.data.cache.model.SectionCM
import com.example.todoist.data.cache.model.TaskCM
import com.example.todoist.data.model.Section
import com.example.todoist.data.model.Task
import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class TodoistCDS @Inject constructor(
    private val paperBook: RxPaperBook
) {

    companion object{
        private const val PROJECTS_KEY = "PROJECTS_KEY"
        private const val SECTIONS_KEY = "SECTION_ID:"
        private const val TASKS_KEY = "TASK_ID:"
    }

    fun upsertProjects(projects: List<ProjectCM>): Completable =
        paperBook.write(PROJECTS_KEY, projects)

    fun getProjects(): Single<List<ProjectCM>> =
        paperBook.read(PROJECTS_KEY)

    fun upsertSections(projectId: Long, sections: List<SectionCM>): Completable =
        paperBook.write("$SECTIONS_KEY$projectId", sections)

    fun getSections(projectId: Long): Single<List<SectionCM>> =
        paperBook.read("$SECTIONS_KEY$projectId")

    fun upsertTasks(sectionId: Long, tasks: List<TaskCM>): Completable =
        paperBook.write("$TASKS_KEY$sectionId", tasks)

    fun getTasks(sectionId: Long): Single<List<TaskCM>> =
        paperBook.read("$TASKS_KEY$sectionId")

}