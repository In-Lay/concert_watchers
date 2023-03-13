package com.inlay.details.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.inlay.details.R
import com.inlay.details.data.asMapsGeo
import com.inlay.details.databinding.FragmentDetailsBinding
import com.inlay.details.presentation.viewModel.DetailsViewModel
import com.inlay.map.presentation.viewModel.MapsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private val detailsViewModel: DetailsViewModel by activityViewModel()
    private val mapsViewModel: MapsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )

//        parentFragmentManager.beginTransaction().add(R.id.maps2, MapsFragment()).commit()
        binding.viewModel = detailsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsViewModel.concertsDetails.observe(viewLifecycleOwner) {
            mapsViewModel.addGeo(it?.detailsLocationModel?.detailsGeoModel?.asMapsGeo())
        }

        mapsViewModel.onMapsFocused.observe(viewLifecycleOwner) {
            if (it) binding.scrollView.setShouldStopInterceptingTouchEvent(true)
        }
    }
}

