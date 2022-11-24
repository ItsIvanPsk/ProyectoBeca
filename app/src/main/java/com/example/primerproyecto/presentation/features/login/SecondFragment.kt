package com.example.primerproyecto.presentation.features.login

import android.R
import android.icu.text.AlphabeticIndex.Record
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.primerproyecto.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    // private val args: SecondFragmentArgs by navArgs()
    private lateinit var viewmodel: LoginViewModel


    var adapter: DataAdapter? = null

    // Nombres
    var names: ArrayList<String> = ArrayList()
    // Apellidos
    var surnames: ArrayList<String> = ArrayList()

    var usernames: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)

        // data to populate the RecyclerView with
        names.add("Nerea")
        names.add("Paco")
        names.add("Alberto")
        names.add("Álvaro")
        names.add("Xavi")
        names.add("Aitor")

        surnames.add("Lopez")
        surnames.add("García")
        surnames.add("Rosa")
        surnames.add("De Miguel")
        surnames.add("Fernandez")
        surnames.add("Mena")

        // set up the RecyclerView
        val recyclerView: RecyclerView = binding.recyclerview
        recyclerView.setLayoutManager(LinearLayoutManager(context));
        adapter = DataAdapter(context, usernames)
        recyclerView.adapter = adapter

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
        addMore.setOnClickListener {
            addRecyclerViewContent()
        }
    }

    fun setupObservers() = with(viewmodel) {
        getUsernameLiveData().observe(viewLifecycleOwner, Observer {
            binding.textToRefresh.text = "Welcome, " + it
            binding.textToRefresh.visibility = View.VISIBLE
            binding.goBackButton.visibility = View.VISIBLE
        })
    }

    fun addRecyclerViewContent(){
        for (i in 0..4) {
            val indexName = (Math.random() * names.size).toInt()
            val indexSurname = (Math.random() * surnames.size).toInt()
            usernames.add(names.get(indexName) + " " + surnames.get(indexSurname))
        }
        // notificar l'adapter dels canvis al model
        adapter!!.notifyDataSetChanged()
    }

}