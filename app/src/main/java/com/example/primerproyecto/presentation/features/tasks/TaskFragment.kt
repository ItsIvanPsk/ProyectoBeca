package com.example.primerproyecto.presentation.features.tasks

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.FragmentTaskBinding
import com.example.primerproyecto.presentation.MainActivity
import com.example.primerproyecto.presentation.features.login.LoginViewModel

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewmodel: LoginViewModel by activityViewModels()

    private var tasks = listOf<Task>()

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
        toolbar.toolbarBackArrow.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    fun setupAdapter(tasks : List<Task>){
        var adapter = DataAdapter()
        val recyclerView: RecyclerView = binding.taskRecycler
        recyclerView.setLayoutManager(LinearLayoutManager(context));
        adapter.tasks = tasks
        recyclerView.adapter = adapter
    }

    fun goToFirstFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToFirstFragment()
        findNavController().navigate(directions)
    }

    fun goToTaskAddFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToAddTaskFragment()
        findNavController().navigate(directions)
    }


    fun setupObservers(){
        viewmodel.getTaskLiveData().observe(viewLifecycleOwner) {
            println(it.size)
            setupAdapter(it)
        }
    }

}
