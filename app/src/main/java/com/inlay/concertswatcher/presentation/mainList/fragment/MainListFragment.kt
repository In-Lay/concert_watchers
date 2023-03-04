package com.inlay.concertswatcher.presentation.mainList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.data.models.ConcertsDataNetworkModel
import com.inlay.concertswatcher.databinding.FragmentMainListBinding
import com.inlay.concertswatcher.presentation.Navigator
import com.inlay.concertswatcher.presentation.mainList.adapter.ConcertsAdapter
import com.inlay.concertswatcher.presentation.mainList.viewModel.MainListViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.core.parameter.parametersOf
import java.io.IOException


class MainListFragment : Fragment() {
    private val viewModel: MainListViewModel by activityViewModel()

    private val navigator: Navigator by inject { parametersOf(activity) }

    private lateinit var binding: FragmentMainListBinding
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
        val jsonString = readJson()
        val testModel = Gson().fromJson(jsonString, ConcertsDataNetworkModel::class.java)

        if (viewModel.concertsData.value == null) viewModel.addTempConcerts(
            testModel
        )

        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        viewModel.itemNetworkModelMutableLiveData.observe(viewLifecycleOwner) { itemModel ->
            itemModel?.let {
                navigator.goToDetails(it)
                viewModel.setItemDataToNull()
            }
        }

        subscribeToData()
    }

    private fun subscribeToData() {
        viewModel.concertsData.observe(viewLifecycleOwner) {
            val concerts = it.concertItemsNetworkModel
            val concertsAdapter = ConcertsAdapter(concerts, "main")

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