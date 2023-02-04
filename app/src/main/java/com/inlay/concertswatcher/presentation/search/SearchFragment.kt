package com.inlay.concertswatcher.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by activityViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search, container, false)
//        binding.datePicker.visibility = GONE

//        binding.addDateOnClick = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        if (binding.venueButton.isChecked) {
//            binding.datePicker.visibility = VISIBLE
//            searchViewModel.addBody(binding.searchView.text.toString())
////            searchViewModel.name.value = binding.venueButton.text.toString()
//            searchViewModel.addName(binding.venueButton.text.toString())
//            searchViewModel.addMinDate(binding.datePicker.minDate)
//            searchViewModel.addMaxDate(binding.datePicker.maxDate)
//        } else if (binding.locationButton.isChecked) {
//            binding.datePicker.visibility = VISIBLE
//            searchViewModel.addBody(binding.searchView.text.toString())
//            searchViewModel.addName(binding.locationButton.text.toString())
//            searchViewModel.addMinDate(binding.datePicker.minDate)
//            searchViewModel.addMaxDate(binding.datePicker.maxDate)
//        } else {
//            binding.datePicker.visibility = GONE
//            searchViewModel.addBody(binding.searchView.text.toString())
//            searchViewModel.addName(binding.artistButton.text.toString())
//        }
        super.onViewCreated(view, savedInstanceState)
    }

//    override fun addDateClicked(view: View) {
//        Log.d("FragmentTag", "AddDate Clicked")
//        val c = Calendar.getInstance()
//
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//        val datePickerDialog = DatePickerDialog(
//            requireContext(), { _, year, monthOfYear, dayOfMonth ->
//
//                //TODO Add dates to ViewModel
//            }, year, month, day
//        )
//        datePickerDialog.show()
//    }
}