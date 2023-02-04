package com.inlay.concertswatcher.presentation.mainList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.data.Data
import com.inlay.concertswatcher.databinding.ConcertItemBinding
import com.inlay.details.presentation.DetailsViewModel
import kotlinx.coroutines.CoroutineScope

class ConcertsAdapter(
    private val dataList: List<Data>?,
    private val scope: CoroutineScope,
    private val detailsViewModel: DetailsViewModel
) : RecyclerView.Adapter<ConcertsDataViewHolder>(), DetailsClickListener {
    private lateinit var binding: ConcertItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertsDataViewHolder {
        binding = ConcertItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcertsDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConcertsDataViewHolder, position: Int) {
        val concert = dataList?.get(position)

        if (concert != null) {
            holder.bind(concert, this)
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size!!
    }

    override fun detailsClicked(view: View, data: Data) {
        val details = data.asDetailsData()
        detailsViewModel.assignDetailsData(details)
        Navigation.findNavController(view).navigate(R.id.action_main_to_detailsFragment)
    }


    companion object {
        @JvmStatic
        @BindingAdapter("imageSource")
        fun loadImage(view: ImageView, image: String) {
            view.load(image)
        }
    }
}
