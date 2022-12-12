package com.example.primerproyecto.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.primerproyecto.databinding.FragmentAddTaskBinding
import com.example.primerproyecto.presentation.features.login.LoginViewModel
import com.example.primerproyecto.presentation.features.tasks.TaskFragmentDirections

class BaseFragment : Fragment(){
    private lateinit var binding: FragmentAddTaskBinding
    private val viewmodel: LoginViewModel by activityViewModels()

    fun getViewModel() : LoginViewModel {
        return viewmodel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        return binding.root
    }

    fun setupListeners() = with(binding){  }

    private fun goToSecondFragment(user : String){
        val directions = TaskFragmentDirections.actionTaskFragmentToFirstFragment()
        findNavController().navigate(directions)
    }

    fun setupObservers(){ }
}