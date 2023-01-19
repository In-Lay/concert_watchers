package com.inlay.concertswatcher.presentation.mainList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inlay.concertswatcher.data.Data
import com.inlay.concertswatcher.databinding.ConcertItemBinding

//class ConcertsAdapter(private val dataList: List<Data>?) :
//    RecyclerView.Adapter<ConcertsDataViewHolder>() {
//    private lateinit var binding: ConcertItemBinding
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertsDataViewHolder {
//        binding = ConcertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ConcertsDataViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ConcertsDataViewHolder, position: Int) {
//        val concert = dataList?.get(position)
//        val location = concert?.location
//        if (concert != null && location != null) {
//            holder.bind(concert, location)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return dataList?.size!!
//    }
//}
class ConcertsAdapter(private val dataList: List<Data>?) :
    RecyclerView.Adapter<ConcertsDataViewHolder>() {
    private lateinit var binding: ConcertItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertsDataViewHolder {
        binding = ConcertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcertsDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConcertsDataViewHolder, position: Int) {
        val concert = dataList?.get(position)
        val location = concert?.location
        if (concert != null && location != null) {
            holder.bind(concert, location)
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }
}