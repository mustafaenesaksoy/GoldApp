package com.enesaksoy.goldapp.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesaksoy.goldapp.R
import com.enesaksoy.goldapp.adapter.IslemAdapter
import com.enesaksoy.goldapp.databinding.IslemFragmentBinding
import com.enesaksoy.goldapp.roomdb.Model
import com.enesaksoy.goldapp.viewmodel.GoldViewModel
import java.text.DecimalFormat
import javax.inject.Inject

class IslemFragment @Inject constructor(private val adapter : IslemAdapter): Fragment(R.layout.islem_fragment) ,MenuProvider{
    private lateinit var binding : IslemFragmentBinding
    private lateinit var viewmodel : GoldViewModel
    private var model : Model? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = IslemFragmentBinding.bind(view)
        viewmodel = ViewModelProvider(requireActivity()).get(GoldViewModel::class.java)
        requireActivity().addMenuProvider(this,viewLifecycleOwner)

        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView2.adapter = adapter
        arguments?.let {
            val id = IslemFragmentArgs.fromBundle(it).id
            viewmodel.getModel(id)

        }
        observeOn()
    }

    private fun observeOn(){
        viewmodel.getmodel.observe(viewLifecycleOwner, Observer {
            model = it
            val islemliste = it.islemList
            var topIsc = 0.0
            var topSonuc = 0.0
            for(list in islemliste){
                topIsc += list.iscilik
                topSonuc += list.hasMiktar
            }
            binding.topIscilikText.text = "Toplam iscilik : ${DecimalFormat("##.###").format(topIsc)}"
            binding.TopSonucText.text = "Toplam sonuc : ${DecimalFormat("##.###").format(topSonuc)}"
            arguments?.let {
                val id = IslemFragmentArgs.fromBundle(it).id
                viewmodel.updateTop(topIsc,topSonuc,id)
            }
            adapter.islemler = islemliste
            adapter.notifyDataSetChanged()
        })
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.islem_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.firmaSil){
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Silmek istedigine eminmisin?")
            alert.setMessage("Silersen firma verilerine bir daha ulaşamazsın.")
            alert.setPositiveButton("Evet",DialogInterface.OnClickListener { dialog, which ->
                model?.let {
                    viewmodel.deleteFirma(model!!)
                    findNavController().popBackStack()
                }
            })
            alert.setNegativeButton("Hayir",DialogInterface.OnClickListener { dialog, which ->
            })
            alert.show()
            return true
        }else if(menuItem.itemId == R.id.islemEkle){
            arguments?.let {
                val id = IslemFragmentArgs.fromBundle(it).id
                val action = IslemFragmentDirections.actionIslemFragmentToAddIslemFragment(id)
                Navigation.findNavController(requireView()).navigate(action)
            }
            return true
        }
        return false
    }
}