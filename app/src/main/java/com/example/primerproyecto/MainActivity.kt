package com.example.primerproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    lateinit var firstFragmentButton: MaterialButton
    lateinit var secondFragmentButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstFragmentButton = findViewById(com.example.primerproyecto.R.id.button_first_fragment)
        secondFragmentButton = findViewById(com.example.primerproyecto.R.id.button_second_fragment)

        changeFragment(R.id.fragmentContainer, FirstFragment())
        settupButtonListeners()

    }

    fun changeFragment(idContainer: Int, fragment: Fragment){
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(idContainer, fragment)
            .commit()
    }

    fun settupButtonListeners(){
        firstFragmentButton.setOnClickListener {
            changeFragment(R.id.fragmentContainer, FirstFragment())
        }
        secondFragmentButton.setOnClickListener {
            changeFragment(R.id.fragmentContainer, SecondFragment())
        }
    }
}