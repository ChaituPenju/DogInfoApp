package com.chaitupenju.dogsinfo.presentation.dog_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chaitupenju.dogsinfo.databinding.ItemDogSummaryBinding
import com.chaitupenju.dogsinfo.domain.model.Dog

class DogListRecyclerView: ListAdapter<Dog, DogListRecyclerView.DogListViewHolder>(object :
    DiffUtil.ItemCallback<Dog>() {
    override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DogListViewHolder(
        ItemDogSummaryBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        )

    override fun onBindViewHolder(holder: DogListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DogListViewHolder(private val dogSummaryBinding: ItemDogSummaryBinding): RecyclerView.ViewHolder(dogSummaryBinding.root) {

        fun bind(dog: Dog) {
            dogSummaryBinding.theDog = dog
        }
    }
}