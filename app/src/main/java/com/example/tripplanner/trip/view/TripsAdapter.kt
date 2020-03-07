package com.example.tripplanner.trip.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Trip
import com.example.tripplanner.R
import kotlinx.android.synthetic.main.trip_item.view.*

class TripsAdapter(val data: List<Trip>) : RecyclerView.Adapter<TripsAdapter.ViewHolder>() {

    val tripClickData = MutableLiveData<Long>()
    fun getTripClickEvent(): LiveData<Long> = tripClickData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trip_item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            itemView.txtTripName.text = data[position].name
            itemView.setOnClickListener(View.OnClickListener {
                tripClickData.value = data[position].id
            })
        }
    }

}