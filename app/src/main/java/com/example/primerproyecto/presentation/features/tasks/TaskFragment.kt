package com.example.primerproyecto.presentation.features.tasks

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl.cancel
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.FragmentTaskBinding
import com.example.primerproyecto.presentation.MainActivity

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewmodel: TaskViewModel by activityViewModels()

    private var tasks = listOf<TaskEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTaskBinding.inflate(layoutInflater)
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    fun setupListeners() = with(binding){
        taskGoBack.setOnClickListener {
            goToFirstFragment()
        }
        taskAddTask.setOnClickListener{
            goToTaskAddFragment()
        }
    }

    fun setupAdapter(tasks : List<TaskEntity>){
        var adapter = DataAdapter(this)
        val recyclerView: RecyclerView = binding.taskRecycler
        adapter.tasks = tasks
        recyclerView.adapter = adapter
    }

    fun goToEditTask(task : TaskEntity){
        viewmodel.setTaskToEdit(task)
        val directions = TaskFragmentDirections.actionTaskFragmentToEditTaskFragment()
        findNavController().navigate(directions)
    }

    fun goToFirstFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToFirstFragment()
        findNavController().navigate(directions)
    }

    fun goToTaskAddFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment()
        findNavController().navigate(directions)
    }

    fun setupObservers() = with(viewmodel){
        getTasks().observe(viewLifecycleOwner){
            setupAdapter(it)
        }
    }

    fun showEditDialog(task: TaskEntity) = with(binding){
        val builder = AlertDialog.Builder(context)
        val inflater = requireActivity().layoutInflater
        builder.setTitle("Edit Task")
        builder.setView(inflater.inflate(R.layout.edit_task_dialog, null))
            .setPositiveButton("Save") { dialog, id ->
                viewmodel.upadateTask(task)
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
