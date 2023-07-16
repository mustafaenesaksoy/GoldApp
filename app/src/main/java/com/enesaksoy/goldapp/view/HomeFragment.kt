package com.enesaksoy.goldapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.enesaksoy.goldapp.R
import com.enesaksoy.goldapp.databinding.HomeFragmentBinding
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.home_fragment) {
    private lateinit var binding: HomeFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        binding.listFirma.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFirmaFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.iscilik.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToToplamIscilikFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }
}