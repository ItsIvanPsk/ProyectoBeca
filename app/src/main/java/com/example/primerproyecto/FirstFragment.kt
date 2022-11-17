package com.example.primerproyecto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.example.primerproyecto.databinding.FragmentFirstBinding
import com.example.primerproyecto.MainActivity.*

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToButton.setOnClickListener {
            if ( binding.mainInput.text.toString() != ""){
                var secondFragment = SecondFragment()
                var bundle = Bundle()
                bundle.putString("input", binding.mainInput.text.toString())
                secondFragment.arguments = bundle
                changeFragment(R.id.fragmentContainer, secondFragment, "firstFragment")
            }
        }
    }

    fun changeFragment(idContainer: Int, fragment: Fragment, backStack: String) {
        parentFragmentManager
            .beginTransaction()
            .replace(idContainer, fragment)
            .addToBackStack(backStack)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
