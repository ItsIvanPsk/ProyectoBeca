package com.example.primerproyecto.features.tasks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.primerproyecto.databinding.FragmentAddTaskBinding
import com.example.primerproyecto.MainActivity
import com.example.primerproyecto.features.tasks.TaskEntity
import com.example.primerproyecto.features.tasks.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment(): Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private val viewmodel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddTaskBinding.inflate(layoutInflater)

        // add back arrow to toolbar
        if ((requireActivity() as MainActivity).supportActionBar != null){
            (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
            (requireActivity() as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true);
        }

        (requireActivity() as MainActivity).supportActionBar?.addOnMenuVisibilityListener {
            println(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        return binding.root
    }

    private fun setupListeners() = with(binding){
        addtaskButtonGoBack.setOnClickListener {
            goToTaskFragment()
        }
        addtaskButtonCreateTask.setOnClickListener {
            var taskName = binding.addtaskInputTaskName.text.toString()
            var taskImage = binding.addtaskCheckboxShowImage.isChecked
            viewmodel.addTask(TaskEntity(taskName = taskName, image = taskImage))
            goToTaskFragment()
        }
    }

    private fun goToTaskFragment(){
        val directions = AddTaskFragmentDirections.actionAddTaskFragmentToTaskFragment()
        findNavController().navigate(directions)
    }

    private fun setupObservers(){ }

}