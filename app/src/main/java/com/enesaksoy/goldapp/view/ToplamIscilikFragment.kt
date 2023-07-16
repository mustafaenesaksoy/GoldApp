package com.enesaksoy.goldapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.enesaksoy.goldapp.R
import com.enesaksoy.goldapp.databinding.ToplamIscilikFragmentBinding
import com.enesaksoy.goldapp.viewmodel.GoldViewModel
import java.text.DecimalFormat

class ToplamIscilikFragment : Fragment(R.layout.toplam_iscilik__fragment) {
    private lateinit var binding: ToplamIscilikFragmentBinding
    private lateinit var viewmodel : GoldViewModel
    private var topIscilik : Double = 0.0
    private var toplamSonuc : Double = 0.0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ToplamIscilikFragmentBinding.bind(view)
        viewmodel = ViewModelProvider(requireActivity()).get(GoldViewModel::class.java)
        observeOn()
    }

    private fun observeOn(){
        viewmodel.getList.observe(viewLifecycleOwner, Observer {
            for(model in it){
                topIscilik += model.topIscilik
                toplamSonuc += model.topSonuc
            }


            binding.iscilikText.text = DecimalFormat("##.###").format(topIscilik).toString()
            binding.sonucText.text = DecimalFormat("##.###").format(toplamSonuc).toString()
        })
    }




}