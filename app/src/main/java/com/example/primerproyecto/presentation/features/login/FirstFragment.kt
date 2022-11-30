package com.example.primerproyecto.presentation.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.primerproyecto.databinding.FragmentFirstBinding
import com.example.primerproyecto.presentation.features.tasks.Task

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewmodel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFirstBinding.inflate(layoutInflater)
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
        goToButton.setOnClickListener {
            viewmodel.checkUsername(mainInput.text.toString())
        }
        goTaskButton.setOnClickListener {
            viewmodel.addTask(Task(0, "Dev1", "Develop something", true))
            viewmodel.addTask(Task(1, "Dev2", "Develop something", false))
            viewmodel.addTask(Task(2, "Dev3", "Develop something", true))
            viewmodel.addTask(Task(3, "Dev4", "Develop something", true))
            viewmodel.addTask(Task(4, "Dev5", "Develop something", false))

            goToTaskFragment()

        }
    }

    private fun goToSecondFragment(user : String){
        val directions = FirstFragmentDirections.actionFirstFragmentToSecondFragment(user)
        view?.findNavController()?.navigate(directions)
    }

    private fun goToTaskFragment(){
        val directions = FirstFragmentDirections.actionFirstFragmentToTaskFragment()
        view?.findNavController()?.navigate(directions)
    }

    fun setupObservers(){
        viewmodel.getCheckLiveData().observe(viewLifecycleOwner) {
            if (it) {
                goToSecondFragment(binding.mainInput.text.toString())
            } else {
                Toast.makeText(context, "User not found!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
