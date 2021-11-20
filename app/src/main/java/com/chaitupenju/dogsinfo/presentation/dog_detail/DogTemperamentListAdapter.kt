package com.chaitupenju.dogsinfo.presentation.dog_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chaitupenju.dogsinfo.common.getLayoutInflater
import com.chaitupenju.dogsinfo.databinding.ItemDogSummaryBinding
import com.chaitupenju.dogsinfo.databinding.ItemDogTemperamentBinding
import com.chaitupenju.dogsinfo.domain.model.Dog
import com.chaitupenju.dogsinfo.presentation.dog_list.DogListAdapter

class DogTemperamentListAdapter: ListAdapter<String, DogTemperamentListAdapter.DogTemperamentViewHolder>(object :
    DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DogTemperamentViewHolder(
        ItemDogTemperamentBinding.inflate(
            parent.getLayoutInflater(),
            parent, false
        )
    )

    override fun onBindViewHolder(holder: DogTemperamentViewHolder, position: Int) {
        val dog = getItem(position)
        holder.bind(dog)
    }

    inner class DogTemperamentViewHolder(private val dogTemperamentBinding: ItemDogTemperamentBinding):
        RecyclerView.ViewHolder(dogTemperamentBinding.root) {

        fun bind(temperament: String) {
            dogTemperamentBinding.temperament = temperament
        }
    }
}