package com.example.primerproyecto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.example.primerproyecto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(R.id.fragmentContainer, FirstFragment(), "firstFragment")
    }

    fun changeFragment(idContainer: Int, fragment: Fragment, backStack: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(idContainer, fragment)
            .addToBackStack(backStack)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()

    }
}