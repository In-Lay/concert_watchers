package com.inlay.concertswatcher.presentation.mainList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.databinding.FragmentMainListBinding
import com.inlay.details.presentation.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException


class MainListFragment : Fragment() {
    private lateinit var binding: FragmentMainListBinding

    private val viewModel: MainListViewModel by viewModel()
    private val detailsViewModel: DetailsViewModel by activityViewModel()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_list, container, false)

        recyclerView = binding.recyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.getConcerts("artist", "Ed Sheeran", 1)
//        val jsonString = readJson()
//        val testModel = Gson().fromJson(jsonString, ConcertsData::class.java)

//        if (viewModel.concertsData.value == null) viewModel.addTempConcerts(testModel)

        if (viewModel.concertsData.value == null) viewModel.getConcerts("artist", "Ed Sheeran", 1)

        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        subscribeToData()
    }

    private fun subscribeToData() {
        viewModel.concertsData.observe(viewLifecycleOwner) {
            val concerts = it.data
            val concertsAdapter = ConcertsAdapter(concerts, lifecycleScope, detailsViewModel)

            recyclerView.adapter = concertsAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(false)
        }
    }

    private fun readJson(): String? {
        val jsonString: String?

        try {
            jsonString =
                context?.assets?.open("sample.json")?.bufferedReader().use { it?.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}