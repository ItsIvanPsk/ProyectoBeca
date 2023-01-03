package com.example.primerproyecto.presentation.task

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.presentation.main_menu.MainActivity
import com.example.primerproyecto.databinding.FragmentTaskBinding
import com.example.primerproyecto.utils.TaskEntity

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewmodel: TaskViewModel by activityViewModels()

    private var tasks = listOf<TaskEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTaskBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        setupAdapter(tasks)
        return binding.root
    }

    private fun setupListeners() = with(binding){
        taskGoBack.setOnClickListener {
            goToFirstFragment()
        }
        taskAddTask.setOnClickListener{
            goToTaskAddFragment()
        }
    }

    private fun setupAdapter(tasks : List<TaskEntity>){
        val adapter = DataAdapter(this)
        val recyclerView: RecyclerView = binding.taskRecycler
        adapter.tasks = tasks
        recyclerView.adapter = adapter
    }

    private fun goToFirstFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToMainMenu()
        findNavController().navigate(directions)
    }

    private fun goToTaskAddFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToMainMenu()
        findNavController().navigate(directions)
    }

    private fun setupObservers() = with(viewmodel){
        getTasks().observe(viewLifecycleOwner){
            setupAdapter(it)
        }
    }

    fun showEditDialog(task: TaskEntity) : AlertDialog = with(binding){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit Task")

        val layout = LinearLayout(context)
        val taskNameInput = EditText(context)
        val imageCheckBox = CheckBox(context)

        taskNameInput.setText(task.taskName)
        imageCheckBox.isChecked = task.image
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(taskNameInput)
        layout.addView(imageCheckBox)


        builder.setView(layout)
            .setPositiveButton("Save") { dialog, id ->
                task.taskName = taskNameInput.text.toString()
                task.image = imageCheckBox.isChecked
                viewmodel.updateTask(task)
            }
            .setNeutralButton("Delete") { dialog, id ->
                viewmodel.deleteTask(task)
            }
            .setNegativeButton("") { dialog, id ->
                dialog.dismiss()
            }

        builder.create()
        builder.show()
    }
}
