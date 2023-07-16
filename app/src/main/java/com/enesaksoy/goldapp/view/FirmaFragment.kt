package com.enesaksoy.goldapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesaksoy.goldapp.R
import com.enesaksoy.goldapp.adapter.ListAdapter
import com.enesaksoy.goldapp.databinding.FirmaFragmentBinding
import com.enesaksoy.goldapp.viewmodel.GoldViewModel
import javax.inject.Inject

class FirmaFragment @Inject constructor(private val adapter: ListAdapter): Fragment(R.layout.firma_fragment){
    private lateinit var binding: FirmaFragmentBinding
    private lateinit var viewmodel : GoldViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FirmaFragmentBinding.bind(view)
        binding.fab.setOnClickListener {
            val action = FirmaFragmentDirections.actionFirmaFragmentToAddFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        viewmodel = ViewModelProvider(requireActivity()).get(GoldViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        observeOn()
    }

    private fun observeOn(){
        viewmodel.getList.observe(viewLifecycleOwner, Observer {
            adapter.modelList = it
            adapter.notifyDataSetChanged()
        })
    }
}