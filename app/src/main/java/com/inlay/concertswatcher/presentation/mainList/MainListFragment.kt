package com.inlay.concertswatcher.presentation.mainList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inlay.concertswatcher.databinding.FragmentMainListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainListFragment : Fragment() {
    private lateinit var binding: FragmentMainListBinding
    private val viewModel: AppMainListViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainListFragmentLog", "Fragment OnCreate")
//        binding = DataBindingUtil.inflate(inflater, R.layout.concert_item, container, false)
        binding = FragmentMainListBinding.inflate(inflater)
        Log.d("MainListFragmentLog", "Fragment Binded")
        recyclerView = binding.recyclerView
        Log.d("MainListFragmentLog", "After recyclerView binded")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MainListFragmentLog", "View Created")
        viewModel.getConcerts("Artist", "Ed Sheeran", 1)
        Log.d("MainListFragmentLog", "getConcerts called")
        viewModel.error.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        Log.d("MainListFragmentLog", "After error observed")
        subscribeToData()
        Log.d("MainListFragmentLog", "After subscribed to Data")
    }

    private fun subscribeToData() {
        viewModel.concertsData.observe(viewLifecycleOwner) {
            val concerts = it.data
            val concertsAdapter = ConcertsAdapter(concerts)

            recyclerView.adapter = concertsAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(false)
        }
    }
}