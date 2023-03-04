package com.inlay.concertswatcher.presentation.mainList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.databinding.ConcertItemBinding
import com.inlay.concertswatcher.presentation.mainList.viewModel.item.ItemViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class ConcertsAdapter(
    private val concertItemNetworkModelList: List<ConcertItemNetworkModel>?,
    private val fragmentType: String
) : RecyclerView.Adapter<ConcertsDataViewHolder>(), KoinComponent {


    private lateinit var binding: ConcertItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertsDataViewHolder {
        binding = ConcertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcertsDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConcertsDataViewHolder, position: Int) {
        if (fragmentType == "main") {
            val itemViewModel: ItemViewModel by inject(named("MainListItem")) { parametersOf(holder.itemView.context) }
            val concert = concertItemNetworkModelList?.get(position)
            itemViewModel.initItemData(concert)
            holder.bind(itemViewModel)
        } else {
            val itemViewModel: ItemViewModel by inject(named("FavouriteListItem")) {
                parametersOf(
                    holder.itemView.context
                )
            }
            val concert = concertItemNetworkModelList?.get(position)
            itemViewModel.initItemData(concert)
            holder.bind(itemViewModel)
        }
    }

    override fun getItemCount(): Int {
        return concertItemNetworkModelList?.size!!
    }
}