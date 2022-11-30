package com.example.primerproyecto.presentation.features.tasks

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.FragmentAddTaskBinding
import com.example.primerproyecto.presentation.MainActivity
import com.example.primerproyecto.presentation.features.login.LoginViewModel

class AddTaskFragment : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private val viewmodel: LoginViewModel by activityViewModels()

    fun getViewModel() : LoginViewModel {
        return viewmodel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        //(requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    fun setupListeners() = with(binding){
        addtaskButtonGoBack.setOnClickListener {
            goToTaskFragment()
        }
        addtaskButtonCreateTask.setOnClickListener {
            //TODO: create task functionality
        }
        toolbar.toolbarBackArrow.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun goToTaskFragment(){
        val directions = AddTaskFragmentDirections.actionAddTaskFragmentToTaskFragment()
        findNavController().navigate(directions)
    }

    fun setupObservers(){ }

}