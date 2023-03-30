package com.example.evaluacin.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.evaluacin.R
import com.example.evaluacin.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMostrar.setOnClickListener(){
            it.findNavController().navigate(R.id.mostrarFragment)
        }

        binding.btnMostrar60.setOnClickListener {
            it.findNavController().navigate(R.id.mostrar60Fragment)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}