package com.example.tripplanner.trip.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.tripplanner.MyApplication
import com.example.tripplanner.R
import com.example.tripplanner.members.view.AddMembersActivity
import com.example.tripplanner.trip.di.DaggerTripComponent
import com.example.tripplanner.trip.viewmodel.TripViewModel
import kotlinx.android.synthetic.main.activity_add_trip.*
import javax.inject.Inject

class AddTripActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    val tripViewModel: TripViewModel by lazy {
        ViewModelProviders.of(this, modelFactory).get(TripViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trip)
        initDagger()
        btnAddTrip.setOnClickListener(this)
        initObservers()
    }

    private fun initDagger() {
        val activityComponent = DaggerTripComponent.builder()
            .appModule((application as MyApplication).getAppModuleData())
            .build()
        activityComponent.inject(this)
    }

    private fun initObservers() {
        tripViewModel.getTripAddedLiveData().observe(this, Observer {
            it?.let {
                navigateToAddMembers(it.id)
            }
        })
        tripViewModel.getErrorLiveData().observe(this, Observer {
            if (it) {
                Toast.makeText(this, getString(R.string.error_try_again), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun navigateToAddMembers(id: Long) {
        val intent = Intent(this, AddMembersActivity::class.java)
        intent.putExtra(AddMembersActivity.KEY_TRIP_ID, id)
        startActivity(intent)
        finish()
    }

    override fun onClick(view: View?) {
        if (etTripName.text.trim().isNotEmpty()) {
            tripViewModel.addTrip(etTripName.text.trim().toString())
        }
    }
}
