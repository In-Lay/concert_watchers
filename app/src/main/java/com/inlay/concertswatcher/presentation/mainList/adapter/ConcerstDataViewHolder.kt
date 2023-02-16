package com.inlay.concertswatcher.presentation.mainList.adapter

import androidx.recyclerview.widget.RecyclerView
import com.inlay.concertswatcher.databinding.ConcertItemBinding
import com.inlay.concertswatcher.presentation.mainList.viewModel.item.ItemViewModel

class ConcertsDataViewHolder(private val binding: ConcertItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        itemViewModel: ItemViewModel
    ) {
        binding.viewModel = itemViewModel
    }
}