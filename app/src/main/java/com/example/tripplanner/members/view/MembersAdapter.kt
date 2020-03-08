package com.example.tripplanner.members.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Member
import com.example.domain.models.Trip
import com.example.tripplanner.R
import kotlinx.android.synthetic.main.member_item.view.*
import kotlinx.android.synthetic.main.trip_item.view.*

class MembersAdapter(val data: List<Member>) : RecyclerView.Adapter<MembersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_item, null)
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
            itemView.txtMemberName.text = data[position].name
            itemView.txtContribution.text =
                itemView.context.getString(R.string.contribution) + " : " + data[position].contribution
            itemView.txtExpanse.text =
                itemView.context.getString(R.string.expense) + " : " + data[position].expanse

        }
    }

}