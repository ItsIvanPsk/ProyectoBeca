package com.example.primerproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.primerproyecto.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val args : SecondFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println(arguments?.getString("name"))
        binding.textToRefresh.text = "Welcome, " + args.username
        binding.goBackButton.setOnClickListener {
            goToFirstFragment()
        }
        return binding.root
    }

    fun goToFirstFragment(){
        view?.findNavController()?.navigate(R.id.action_secondFragment_to_firstFragment)
    }

}