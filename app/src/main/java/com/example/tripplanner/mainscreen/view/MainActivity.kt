package com.example.tripplanner.mainscreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tripplanner.R
import com.example.tripplanner.trip.view.AddTripActivity
import com.example.tripplanner.trip.view.TripsListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAddTrip.setOnClickListener(this)
        btnViewTrip.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnAddTrip -> {
                val intent = Intent(this, AddTripActivity::class.java)
                startActivity(intent)
            }
            R.id.btnViewTrip -> {
                val intent = Intent(this, TripsListActivity::class.java)
                startActivity(intent)

            }
        }
    }
}
