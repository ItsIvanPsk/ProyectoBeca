package com.example.primerproyecto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.example.primerproyecto.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeFragment(R.id.fragmentContainer, FirstFragment(), "firstFragment")
        settupButtonListeners()

    }

    fun changeFragment(idContainer: Int, fragment: Fragment, backStack: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(idContainer, fragment)
            .addToBackStack(backStack)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    fun settupButtonListeners() = with(binding) {
        buttonFirstFragment.setOnClickListener {
            changeFragment(R.id.fragmentContainer, FirstFragment(), "firstFragment")
        }
        buttonSecondFragment.setOnClickListener {
            changeFragment(R.id.fragmentContainer, SecondFragment(), "secondFragment")
        }
    }
}