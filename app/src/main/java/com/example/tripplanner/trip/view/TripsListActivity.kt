package com.example.tripplanner.trip.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.Trip
import com.example.tripplanner.MyApplication
import com.example.tripplanner.R
import com.example.tripplanner.members.view.AddMembersActivity
import com.example.tripplanner.members.view.MembersListActivity
import com.example.tripplanner.trip.di.DaggerTripComponent
import com.example.tripplanner.trip.viewmodel.TripViewModel
import kotlinx.android.synthetic.main.activity_trips_list.*
import javax.inject.Inject

class TripsListActivity : AppCompatActivity() {
    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    val tripViewModel: TripViewModel by lazy {
        ViewModelProviders.of(this, modelFactory).get(TripViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips_list)
        initDagger()
        initObservers()
        tripViewModel.getTrips()
    }

    private fun initDagger() {
        val activityComponent = DaggerTripComponent.builder()
            .appModule((application as MyApplication).getAppModuleData())
            .build()
        activityComponent.inject(this)
    }

    private fun initObservers() {
        tripViewModel.getAllTripsLiveData().observe(this, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    setupAdapter(it)
                } else {
                    rvTrips.visibility = View.GONE
                    txtNoTrips.visibility = View.VISIBLE
                }
            }
        })

        tripViewModel.getErrorLiveData().observe(this, Observer {
            if(it){
                Toast.makeText(this, getString(R.string.error_try_again), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupAdapter(it: List<Trip>) {
        val adapter = TripsAdapter(it)
        rvTrips.layoutManager = LinearLayoutManager(this)
        rvTrips.adapter = adapter
        adapter.getTripClickEvent().observe(this, Observer {
            it?.let {
                val intent = Intent(this, MembersListActivity::class.java)
                intent.putExtra(AddMembersActivity.KEY_TRIP_ID, it)
                startActivity(intent)
            }

        })
    }
}
