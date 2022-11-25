package com.example.primerproyecto.presentation.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.FragmentSecondBinding
import com.example.primerproyecto.presentation.features.tasks.DataAdapter


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    // private val args: SecondFragmentArgs by navArgs()
    private lateinit var viewmodel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)

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

    fun goToFirstFragment(){
        val directions = SecondFragmentDirections.actionSecondFragmentToFirstFragment()
        view?.findNavController()?.navigate(directions)
    }

    fun setupListeners() = with(binding){
        goBackButton.setOnClickListener {
            goToFirstFragment()
        }
    }

    fun setupObservers() = with(viewmodel) {
        getUsernameLiveData().observe(viewLifecycleOwner, Observer {
            binding.textToRefresh.text = "Welcome, " + it
            binding.textToRefresh.visibility = View.VISIBLE
            binding.goBackButton.visibility = View.VISIBLE
        })
    }
}