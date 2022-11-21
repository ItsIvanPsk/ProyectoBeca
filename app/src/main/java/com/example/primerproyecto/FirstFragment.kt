package com.example.primerproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.primerproyecto.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFirstBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.goToButton.setOnClickListener {
            goToSecondFragment()
        }
        return binding.root
    }

    fun goToSecondFragment(){
        val directions = FirstFragmentDirections.actionFirstFragmentToSecondFragment(binding.mainInput.text.toString())
        view?.findNavController()?.navigate(directions)
    }

}
