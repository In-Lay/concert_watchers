package com.inlay.concertswatcher.presentation.mainList

import androidx.recyclerview.widget.RecyclerView
import com.inlay.concertswatcher.data.Data
import com.inlay.concertswatcher.databinding.ConcertItemBinding

class ConcertsDataViewHolder(private val binding: ConcertItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: Data?,
        detailsClickListener: DetailsClickListener
    ) {
        binding.data = data
        binding.detailsClickListener = detailsClickListener
    }
}