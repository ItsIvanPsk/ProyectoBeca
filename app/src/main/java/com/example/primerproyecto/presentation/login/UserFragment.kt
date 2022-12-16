package com.example.primerproyecto.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.primerproyecto.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewmodel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setupListeners()
        setupObservers()
        return binding.root
    }

    private fun goToMainMenuFragment(){
        val directions = UserFragmentDirections.actionUserFragmentToMainMenu()
        findNavController().navigate(directions)
    }

    private fun setupListeners() = with(binding){
        goBackButton.setOnClickListener {
            viewmodel.setCheckLiveData(false)
            viewmodel.setUsernameLiveData("")
            goToMainMenuFragment()

        }
    }

    private fun setupObservers() = with(viewmodel) {
        getUsernameLiveData().observe(viewLifecycleOwner, Observer {
            binding.textToRefresh.text = "Welcome, " + it
            binding.textToRefresh.visibility = View.VISIBLE
            binding.goBackButton.visibility = View.VISIBLE
        })
    }
}