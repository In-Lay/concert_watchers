package com.inlay.details

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.inlay.details.data.models.DetailsDataModel
import com.inlay.details.databinding.ActivityDetailsBinding
import com.inlay.details.presentation.viewModel.DetailsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModel()
    private val database: FirebaseDatabase by inject()
    private lateinit var valueListener: ValueEventListener
    private lateinit var databaseReference: DatabaseReference
    private var itemKey: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = database.reference

        detailsViewModel.setIconImage(
            AppCompatResources.getDrawable(
                applicationContext, R.drawable.outline_favorite_border_24
            )!!
        )

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataModelItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("dataModelItem", DetailsDataModel::class.java)
        } else {
            intent.getParcelableExtra("dataModelItem")
        }

        detailsViewModel.assignDetailsData(dataModelItem)


        if (Firebase.auth.currentUser == null) {
            binding.favIcon.visibility = View.GONE
        } else {
            val currentUserId = Firebase.auth.currentUser?.uid

            valueListener = databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val findConcert =
                        snapshot.child("concerts").child(currentUserId!!).children.find {
                            val itemDetails = it.getValue(DetailsDataModel::class.java)
                            dataModelItem?.startDate == itemDetails?.startDate && dataModelItem?.detailsLocationModel?.detailsGeoModel?.latitude == itemDetails?.detailsLocationModel?.detailsGeoModel?.latitude && dataModelItem?.detailsLocationModel?.detailsGeoModel?.longitude == itemDetails?.detailsLocationModel?.detailsGeoModel?.longitude
                        }

                    if (findConcert != null) {
                        itemKey = findConcert.key!!

                        detailsViewModel.setIconImage(
                            AppCompatResources.getDrawable(
                                applicationContext, R.drawable.outline_favorite_24
                            )!!
                        )
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

            detailsViewModel.favButtonFlag.observe(this) {
                if (it && itemKey == "") {
                    detailsViewModel.setIconImage(
                        AppCompatResources.getDrawable(
                            applicationContext, R.drawable.outline_favorite_24
                        )!!
                    )
                    databaseReference.child("concerts").child(currentUserId!!).push()
                        .setValue(dataModelItem).addOnSuccessListener {
                            Toast.makeText(
                                applicationContext, "Added to favourites", Toast.LENGTH_SHORT
                            ).show()
                        }.addOnFailureListener {
                            Toast.makeText(applicationContext, "Adding failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                } else {
                    detailsViewModel.setIconImage(
                        AppCompatResources.getDrawable(
                            applicationContext, R.drawable.outline_favorite_border_24
                        )!!
                    )
                    databaseReference.child("concerts").child(currentUserId!!).child(itemKey)
                        .removeValue().addOnSuccessListener {
                            Toast.makeText(
                                applicationContext, "Removed from favourites", Toast.LENGTH_SHORT
                            ).show()
                        }.addOnFailureListener {
                            Toast.makeText(
                                applicationContext, "Removing failed", Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
        }

        binding.lifecycleOwner = this

        binding.detailsViewModel = detailsViewModel
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        databaseReference.removeEventListener(valueListener)
        super.onDestroy()
    }

    companion object {
        fun getStartIntent(activity: Activity): Intent {
            return Intent(activity, DetailsActivity::class.java)
        }
    }
}