package com.example.primerproyecto.presentation.features.login

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
import com.example.primerproyecto.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewmodel: LoginViewModel by activityViewModels()

    fun getViewModel() : LoginViewModel{
        return viewmodel
    }

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
    }

    fun goToSecondFragment(user : String){
        val directions = FirstFragmentDirections.actionFirstFragmentToSecondFragment(user)
        view?.findNavController()?.navigate(directions)
    }

    fun setupObservers(){
        viewmodel.getCheckLiveData().observe(viewLifecycleOwner, Observer {
           if (it){
               goToSecondFragment(binding.mainInput.text.toString())
           } else{
               Toast.makeText(context, "User not found!", Toast.LENGTH_LONG).show()
           }
        })
    }
}
