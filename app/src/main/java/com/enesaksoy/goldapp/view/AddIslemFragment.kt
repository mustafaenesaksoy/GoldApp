package com.enesaksoy.goldapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enesaksoy.goldapp.R
import com.enesaksoy.goldapp.databinding.AddIslemFragmentBinding
import com.enesaksoy.goldapp.roomdb.IslemListesiModel
import com.enesaksoy.goldapp.viewmodel.GoldViewModel
import java.text.DecimalFormat

class AddIslemFragment: Fragment(R.layout.add_islem_fragment) {
    private lateinit var binding : AddIslemFragmentBinding
    private lateinit var viewModel : GoldViewModel
    private var id : Int? = null
    private var list : List<IslemListesiModel>? = null
    private var milyem : Double? = null
    private var miktar : Double? = null
    private var iscilik : Double? = null
    private var sonuc : Double? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddIslemFragmentBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(GoldViewModel::class.java)

        binding.sonucHesapla.setOnClickListener {
            milyem = binding.milyem.text.toString().toDouble()
            miktar = binding.miktar.text.toString().toDouble()
            iscilik = binding.iscilik.text.toString().toDouble()
            sonuc = miktar!! * (milyem!! + iscilik!!)
            DecimalFormat("##.###").format(sonuc)
            binding.sonucText.text = sonuc.toString()
        }

        binding.kaydet.setOnClickListener {
            val tarih = binding.tarih.text.toString()
            if(binding.AldimCheckBox.isChecked){
                iscilik = iscilik!! * (-1)
                val arrayList = ArrayList<IslemListesiModel>()
                for(lsi in list!!){
                    arrayList.add(lsi)
                }
                val islem = IslemListesiModel("A",tarih,miktar!!,milyem!!,iscilik!!,sonuc!!)
                arrayList.add(islem)
                viewModel.updateModel(arrayList,id!!)
                findNavController().popBackStack()
            }else if(binding.VerdimCheckBox.isChecked){
                sonuc = sonuc!! * (-1)
                val arrayList = ArrayList<IslemListesiModel>()
                for(lsi in list!!){
                    arrayList.add(lsi)
                }
                val islem = IslemListesiModel("V",tarih,miktar!!,milyem!!,iscilik!!,sonuc!!)
                arrayList.add(islem)
                viewModel.updateModel(arrayList,id!!)
                findNavController().popBackStack()
            }
        }

        arguments?.let {
           id = AddIslemFragmentArgs.fromBundle(it).id
            viewModel.getModel(id!!)
        }


        observeOn()
    }

    private fun observeOn(){
        viewModel.getmodel.observe(viewLifecycleOwner, Observer {
            list = it.islemList
        })
    }
}