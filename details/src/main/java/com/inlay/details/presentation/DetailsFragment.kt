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
import com.inlay.details.R
import com.inlay.details.data.Geo
import com.inlay.details.databinding.FragmentDetailsBinding
import com.inlay.map.presentation.MapsFragment
import com.inlay.map.presentation.MapsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class DetailsFragment : Fragment(), FavClickListener {

    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel: DetailsViewModel by activityViewModel()
    private val mapsViewModel: MapsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )
//        (context as? AppCompatActivity)?.supportActionBar?.hide()
//        (context as? AppCompatActivity)?.setSupportActionBar()
        parentFragmentManager.beginTransaction().add(R.id.maps_layout, MapsFragment()).commit()

        return binding.root
    }

    private fun Geo.asMapsGeo(): com.inlay.map.data.Geo {
        return com.inlay.map.data.Geo(type, latitude, longitude)
    }

    override fun onClick(view: View) {
        Toast.makeText(context, "Added", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        detailsViewModel.concertsDetails.observe(viewLifecycleOwner) {
            binding.data = it
            binding.favOnClickListener = this
            mapsViewModel.addGeo(it.location.geo.asMapsGeo())
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageSource")
        fun loadImage(view: ImageView, image: String) {
            view.load(image)
        }
    }
}