package com.example.primerproyecto.features.tasks.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.FragmentTaskBinding
import com.example.primerproyecto.MainActivity
import com.example.primerproyecto.features.tasks.TaskEntity
import com.example.primerproyecto.features.tasks.TaskViewModel
import com.example.primerproyecto.features.tasks.data.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    private fun setupListeners() = with(binding){
        taskGoBack.setOnClickListener {
            goToFirstFragment()
        }
        taskAddTask.setOnClickListener{
            goToTaskAddFragment()
        }
    }

    private fun setupAdapter(tasks : List<TaskEntity>){
        var adapter = DataAdapter(this)
        val recyclerView: RecyclerView = binding.taskRecycler
        adapter.tasks = tasks
        recyclerView.adapter = adapter
    }

    private fun goToFirstFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToFirstFragment()
        findNavController().navigate(directions)
    }

    private fun goToTaskAddFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment()
        findNavController().navigate(directions)
    }

    private fun setupObservers() = with(viewmodel){
        getTasks().observe(viewLifecycleOwner){
            setupAdapter(it)
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
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
