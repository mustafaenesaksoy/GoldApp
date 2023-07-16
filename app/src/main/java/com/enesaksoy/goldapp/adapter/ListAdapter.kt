    package com.enesaksoy.goldapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enesaksoy.goldapp.databinding.FirmaRowBinding
import com.enesaksoy.goldapp.roomdb.Model
import com.enesaksoy.goldapp.view.FirmaFragmentDirections
import javax.inject.Inject

class ListAdapter @Inject constructor() : RecyclerView.Adapter<ListAdapter.ListHolder>() {
    class ListHolder (val binding : FirmaRowBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffutil = object : DiffUtil.ItemCallback<Model>(){
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model) : Boolean {
            return oldItem == newItem
        }
    }

    private val recycleList = AsyncListDiffer(this,diffutil)

    var modelList : List<Model>
    get() = recycleList.currentList
    set(value) = recycleList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = FirmaRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }



    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val model = modelList.get(position)
        holder.binding.firmaRowName.text = model.name
        holder.itemView.setOnClickListener {
            val action = FirmaFragmentDirections.actionFirmaFragmentToIslemFragment(model.id!!)
            Navigation.findNavController(it).navigate(action)
        }
    }
}