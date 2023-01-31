package com.inlay.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import coil.load
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.inlay.details.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class DetailsFragment : Fragment(), FavClickListener, OnMapReadyCallback {

    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel: DetailsViewModel by activityViewModel()
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, com.inlay.details.R.layout.fragment_details, container, false
        )

        detailsViewModel.concertsDetails.observe(viewLifecycleOwner) {
            binding.data = it
            binding.favOnClickListener = this
        }

        return binding.root
    }

    override fun onClick(view: View) {
        Toast.makeText(context, "Added", Toast.LENGTH_LONG).show()
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageSource")
        fun loadImage(view: ImageView, image: String) {
            view.load(image)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}