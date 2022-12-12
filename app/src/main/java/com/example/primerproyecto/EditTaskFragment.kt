package com.example.primerproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.primerproyecto.databinding.FragmentEditTaskBinding
import com.example.primerproyecto.presentation.MainActivity
import com.example.primerproyecto.presentation.features.tasks.TaskEntity
import com.example.primerproyecto.presentation.features.tasks.TaskFragmentDirections
import com.example.primerproyecto.presentation.features.tasks.TaskViewModel

class EditTaskFragment : Fragment() {

    private lateinit var binding: FragmentEditTaskBinding
    private val viewmodel: TaskViewModel by activityViewModels()
    private lateinit var task : TaskEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentEditTaskBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        task = viewmodel.getTaskToEdit().value!!
        setupListeners()
        setupObservers()
        setupTaskToEdit(task)
        return binding.root
    }

    fun setupListeners() = with(binding){
        editTaskButtonSaveTask.setOnClickListener {
            println(task.taskId)
            viewmodel.upadateTask(task)
            goToTaskFragment()
        }
        editTaskButtonDeleteTask.setOnClickListener {
            viewmodel.deleteTask(task)
            goToTaskFragment()
        }
    }

    fun setupTaskToEdit(task : TaskEntity) = with(binding){
        editTaskCheckboxShowImage.isChecked = task.image
        editTaskInputTaskName.setText(task.taskName)
    }

    fun goToTaskFragment(){
        val directions = EditTaskFragmentDirections.actionEditTaskFragmentToTaskFragment()
        findNavController().navigate(directions)
    }

    fun setupObservers() = with(viewmodel){
        getTaskToEdit().observe(viewLifecycleOwner){
            task = it
            println("observer")
        }
    }
}
