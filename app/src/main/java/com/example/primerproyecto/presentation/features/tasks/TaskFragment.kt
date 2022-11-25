package com.example.primerproyecto.presentation.features.login

import android.app.appsearch.StorageInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.FragmentFirstBinding
import com.example.primerproyecto.databinding.FragmentTaskBinding
import com.example.primerproyecto.presentation.features.tasks.DataAdapter

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewmodel: LoginViewModel by activityViewModels()

    var adapter: DataAdapter? = null

    var tasks = arrayOf("Dev android")

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
        viewmodel.addTask("Devosi")
        return binding.root
    }

    fun setupListeners() = with(binding){
        taskGoBack.setOnClickListener {
            goToFirstFragment()
        }
    }

    fun setupAdapter(tasks : Array<String>){
        val recyclerView: RecyclerView = binding.recyclerview
        recyclerView.setLayoutManager(LinearLayoutManager(context));
        adapter = DataAdapter(context, tasks)
        recyclerView.adapter = adapter
    }

    fun goToFirstFragment(){
        val directions = TaskFragmentDirections.actionTaskFragmentToFirstFragment()
        view?.findNavController()?.navigate(directions)
    }

    fun setupObservers(){
        viewmodel.getTaskLiveData().observe(viewLifecycleOwner) {
            println(it.size)
            setupAdapter(it)
        }
    }
}
