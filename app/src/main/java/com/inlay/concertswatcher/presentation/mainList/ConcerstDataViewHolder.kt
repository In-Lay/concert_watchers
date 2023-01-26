package com.inlay.concertswatcher.presentation.mainList

import androidx.recyclerview.widget.RecyclerView
import com.inlay.concertswatcher.data.Data
import com.inlay.concertswatcher.data.Location
import com.inlay.concertswatcher.databinding.ConcertItemBinding

class ConcertsDataViewHolder(private val binding: ConcertItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: Data?, location: Location,
//             detailsClickListener: DetailsClickListener,
    ) {
        binding.data = data
        binding.location = location
//        binding.detailsClickListener = detailsClickListener
    }
}