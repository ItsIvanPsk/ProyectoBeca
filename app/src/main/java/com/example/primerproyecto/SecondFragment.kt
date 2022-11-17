package com.example.primerproyecto

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.example.primerproyecto.databinding.ActivityMainBinding
import com.example.primerproyecto.databinding.FragmentFirstBinding
import com.example.primerproyecto.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textToRefresh.text = "Welcome, " + this.arguments?.get("input").toString() + "!"
        binding.goBackButton.setOnClickListener {
            changeFragment(
                R.id.fragmentContainer,
                FirstFragment(),
                "secondFragment"
            )
        }
    }

    fun changeFragment(idContainer: Int, fragment: Fragment, backStack: String) {
        parentFragmentManager
            .beginTransaction()
            .replace(idContainer, fragment)
            .addToBackStack(backStack)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}