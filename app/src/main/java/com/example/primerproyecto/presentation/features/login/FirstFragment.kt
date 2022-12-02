package com.example.primerproyecto.presentation.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.primerproyecto.databinding.FragmentFirstBinding
import com.example.primerproyecto.presentation.features.tasks.TaskEntity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

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
            if (it && it != null) {
                goToSecondFragment(binding.mainInput.text.toString())
            } else if (it){
                Toast.makeText(context, "User not found!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
