package com.enesaksoy.goldapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enesaksoy.goldapp.databinding.IslemRowBinding
import com.enesaksoy.goldapp.roomdb.IslemListesiModel
import java.text.DecimalFormat
import javax.inject.Inject

class IslemAdapter @Inject constructor(): RecyclerView.Adapter<IslemAdapter.IslemHolder>() {

    private val diffutil = object : DiffUtil.ItemCallback<IslemListesiModel>(){
        override fun areItemsTheSame(
            oldItem: IslemListesiModel,
            newItem: IslemListesiModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: IslemListesiModel,
            newItem: IslemListesiModel
        ): Boolean {
            return oldItem == newItem
        }
    }
    val recyclerlist = AsyncListDiffer(this,diffutil)
    var islemler : List<IslemListesiModel>
    get() = recyclerlist.currentList
    set(value) = recyclerlist.submitList(value)
    class IslemHolder(val binding : IslemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IslemHolder {
        val binding = IslemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return IslemHolder(binding)
    }

    override fun getItemCount(): Int {
        return islemler.size
    }

    override fun onBindViewHolder(holder: IslemHolder, position: Int) {
        val islem = islemler.get(position)
        islem.hasMiktar

        holder.binding.durum.text = "Durum : ${islem.durum}"
        holder.binding.tarih.text = "tarih : ${islem.tarih}"
        holder.binding.miktar.text = "miktar : ${islem.miktar}"
        holder.binding.iscilik.text = "iscilik : ${islem.iscilik}"
        holder.binding.milyem.text = "milyem : ${islem.milyem}"
        holder.binding.hasSonuc.text = "Sonuc : ${DecimalFormat("##.##").format(islem.hasMiktar)}"
    }
}