package com.example.todoist.presentation.scene.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoist.R
import com.example.todoist.common.TodoistApplication
import com.example.todoist.databinding.ActivityMainBinding
import com.example.todoist.presentation.common.Screens
import com.example.todoist.presentation.scene.projects.ProjectsFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigationHolder:NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as TodoistApplication).applicationComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        router.replaceScreen(Screens.projectsScreen())
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }
}