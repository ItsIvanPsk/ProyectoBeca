package com.example.primerproyecto.presentation.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.primerproyecto.databinding.FragmentAddTaskBinding
import com.example.primerproyecto.presentation.main_menu.MainActivity
import com.example.primerproyecto.utils.TaskEntity
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
            val taskName = binding.addtaskInputTaskName.text.toString()
            val taskImage = binding.addtaskCheckboxShowImage.isChecked
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