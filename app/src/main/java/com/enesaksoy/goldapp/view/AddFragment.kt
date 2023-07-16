package com.enesaksoy.goldapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.enesaksoy.goldapp.R
import com.enesaksoy.goldapp.databinding.AddFirmaFragmentBinding
import com.enesaksoy.goldapp.roomdb.IslemListesiModel
import com.enesaksoy.goldapp.roomdb.Model
import com.enesaksoy.goldapp.viewmodel.GoldViewModel
import java.text.DecimalFormat

class AddFragment : Fragment(R.layout.add_firma_fragment) {
    private lateinit var binding : AddFirmaFragmentBinding
    private lateinit var viewModel : GoldViewModel
    private var milyem : Double? = null
    private var miktar : Double? = null
    private var iscilik : Double? = null
    private var sonuc : Double? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddFirmaFragmentBinding.bind(view)
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
            val name = binding.firmaName.text.toString()
            val tarih = binding.tarih.text.toString()
            if(binding.AldimCheckBox.isChecked){
                iscilik = iscilik!! * (-1)
                val islemList = arrayListOf(IslemListesiModel("A",tarih,miktar!!,milyem!!,iscilik!!,sonuc!!))
                val model = Model(name,islemList,iscilik!!,sonuc!!)
                viewModel.insertFirma(model)
                findNavController().popBackStack()
            }else if(binding.VerdimCheckBox.isChecked){
                sonuc = sonuc!! * (-1)
                val islemList = arrayListOf(IslemListesiModel("V",tarih,miktar!!,milyem!!,iscilik!!,sonuc!!))
                val model = Model(name,islemList,iscilik!!,sonuc!!)
                viewModel.insertFirma(model)
                findNavController().popBackStack()
            }
        }
    }
}