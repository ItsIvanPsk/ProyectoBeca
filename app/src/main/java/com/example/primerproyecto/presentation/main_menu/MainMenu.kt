package com.example.primerproyecto.presentation.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.primerproyecto.databinding.FragmentMainMenuBinding
import com.example.primerproyecto.presentation.login.LoginViewModel

class MainMenu : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private val viewmodel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupListeners()
        setupObservers()
        return binding.root
    }

    private fun setupListeners() = with(binding){
        goToButton.setOnClickListener {
            viewmodel.checkUsername(mainInput.text.toString())
        }
        goTaskButton.setOnClickListener {
            goToTaskFragment()
        }
        goPokemonButton.setOnClickListener {
            goToPokemonFragment()
        }
    }

    private fun goToLoginFragment(){
        val directions = MainMenuDirections.actionMainMenuToLoginGraph()
        findNavController().navigate(directions)
    }

    private fun goToTaskFragment(){
        val directions = MainMenuDirections.actionMainMenuToTaskGraph()
        findNavController().navigate(directions)
    }

    private fun goToPokemonFragment(){
        val directions = MainMenuDirections.actionMainMenuToPokemonGraph()
        findNavController().navigate(directions)
    }

    private fun setupObservers(){
        viewmodel.getCheckLiveData().observe(viewLifecycleOwner) {
            if (it) {
                goToLoginFragment()
            } else {
                Toast.makeText(context, "User not found!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
