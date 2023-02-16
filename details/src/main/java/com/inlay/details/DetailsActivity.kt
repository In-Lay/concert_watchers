package com.inlay.details

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.inlay.details.data.models.DetailsDataModel
import com.inlay.details.databinding.ActivityDetailsBinding
import com.inlay.details.presentation.viewModel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataModelItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("dataModelItem", DetailsDataModel::class.java)
        } else {
            intent.getParcelableExtra("dataModelItem")
        }
        detailsViewModel.assignDetailsData(dataModelItem)

        binding.detailsViewModel = detailsViewModel
    }

    //TODO Handle onBackPressed

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun getStartIntent(activity: Activity): Intent {
            return Intent(activity, DetailsActivity::class.java)
        }
    }
}