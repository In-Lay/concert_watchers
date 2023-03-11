package com.inlay.concertswatcher.presentation.favouriteList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.data.models.ConcertItemNetworkModel
import com.inlay.concertswatcher.databinding.FragmentFavouriteBinding
import com.inlay.concertswatcher.presentation.Navigator
import com.inlay.concertswatcher.presentation.ext.asMainItemData
import com.inlay.concertswatcher.presentation.favouriteList.viewModel.FavouriteListViewModel
import com.inlay.concertswatcher.presentation.mainList.adapter.ConcertsAdapter
import com.inlay.details.data.models.DetailsDataModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.core.parameter.parametersOf

class FavouriteListFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var recyclerView: RecyclerView
    private val favouritesViewModel: FavouriteListViewModel by activityViewModel()
    private val database: FirebaseDatabase by inject()
    private var valueListener: ValueEventListener? = null
    private lateinit var databaseReference: DatabaseReference
    private val favouriteList = mutableListOf<ConcertItemNetworkModel>()
    private val navigator: Navigator by inject { parametersOf(activity) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        recyclerView = binding.recyclerView
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentUser = Firebase.auth.currentUser
        databaseReference = database.reference

        if (currentUser == null) {
            Toast.makeText(context, "Login to view your favourites", Toast.LENGTH_LONG).show()
        } else {
            valueListener = databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    favouriteList.clear()
                    snapshot.child("concerts").child(currentUser.uid).children.forEach {
                        val concert = it.getValue(DetailsDataModel::class.java)
                        if (concert != null) {
                            favouriteList.add(concert.asMainItemData())
                            favouritesViewModel.updateConcertsList(favouriteList)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

            subscribeToData()

            favouritesViewModel.itemNetworkModelMutableLiveData.observe(viewLifecycleOwner) { itemModel ->
                itemModel?.let {
                    navigator.goToDetails(it)
                    favouritesViewModel.setItemDataToNull()
                }
            }
        }
    }

    override fun onDestroy() {
        if (valueListener != null) {
            databaseReference.removeEventListener(valueListener!!)
        }
        super.onDestroy()
    }

    private fun subscribeToData() {
        favouritesViewModel.favouriteConcerts.observe(viewLifecycleOwner) {
            val concertsAdapter = ConcertsAdapter(it, "favourite")

            recyclerView.adapter = concertsAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(false)
        }
    }
}