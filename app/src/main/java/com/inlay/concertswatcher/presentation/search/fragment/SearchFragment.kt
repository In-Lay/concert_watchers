package com.inlay.concertswatcher.presentation.search.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.databinding.FragmentSearchBinding
import com.inlay.concertswatcher.di.getOrCreateSearchScope
import com.inlay.concertswatcher.presentation.search.viewModel.SearchViewModel
import java.io.IOException

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel = getOrCreateSearchScope().get()

    private var modelMinDate: String? = ""
    private var modelMaxDate: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_search, container, false
        )
        binding.viewModel = searchViewModel
        binding.dateButton.visibility = GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var modelPath = PathEnum.ARTIST.path

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.artist_button -> {
                    binding.dateButton.visibility = GONE
                    modelPath = PathEnum.ARTIST.path
                }
                R.id.location_button -> {
                    binding.dateButton.visibility = VISIBLE
                    modelPath = PathEnum.LOCATION.path
                }
                else -> {
                    binding.dateButton.visibility = VISIBLE
                    modelPath = PathEnum.VENUE.path
                }
            }
        }

        searchViewModel.onDatePickerClickedFlag.observe(viewLifecycleOwner) {
            if (it == true) {
                val datePickerFragment = DatePickerFragment(onDatePicked)
                datePickerFragment.show(parentFragmentManager, "date picker")
            }
        }
        val jsonString = readJson()
        val testModel = Gson().fromJson(jsonString, ConcertsDataNetworkModel::class.java)

        binding.searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (modelPath == PathEnum.VENUE.path && modelMinDate == null || modelPath == PathEnum.LOCATION.path && modelMinDate == null) {
                    Toast.makeText(context, "Pick date first", Toast.LENGTH_LONG).show()
                } else {
//                    searchViewModel.sendSearchUiModel(
//                        SearchUiModel(
//                            modelPath, v.text.toString(), modelMinDate, modelMaxDate
//                        )
//                    )
                    searchViewModel.addTempConcerts(testModel)
                }
            }
            true
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private val onDatePicked: (String?, String?) -> Unit = { minDate, maxDate ->
        modelMinDate = minDate
        modelMaxDate = maxDate
    }

    private fun readJson(): String? {
        val jsonString: String?

        try {
            jsonString =
                context?.assets?.open("sample2.json")?.bufferedReader().use { it?.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    private enum class PathEnum(val path: String) {
        ARTIST("artist"), LOCATION("location"), VENUE("venue/past")
    }
}