package com.example.tripplanner.members.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.Member
import com.example.domain.models.MembersListChangeEvent
import com.example.tripplanner.MyApplication
import com.example.tripplanner.R
import com.example.tripplanner.members.di.DaggerMembersComponent
import com.example.tripplanner.members.viewmodel.MembersViewModel
import com.example.tripplanner.trip.view.TripsAdapter
import com.example.tripplanner.utils.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import kotlinx.android.synthetic.main.activity_members_list.*
import kotlinx.android.synthetic.main.activity_trips_list.*
import javax.inject.Inject

class MembersListActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    val membersViewModel: MembersViewModel by lazy {
        ViewModelProviders.of(this, modelFactory).get(MembersViewModel::class.java)
    }
    @Inject
    lateinit var rxBus: RxBus

    var tripId: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initDagger()
        initObserver()
        rxBus.register(this)
        tripId = intent?.extras?.getLong(AddMembersActivity.KEY_TRIP_ID) ?: 0
        membersViewModel.getAllMembers(tripId)
        btnAddMember.setOnClickListener(this)
    }

    @Subscribe()
    fun onChangeMemList(event: MembersListChangeEvent) {
        membersViewModel.getAllMembers(tripId)
    }

    override fun onDestroy() {
        rxBus.unRegister(this)
        super.onDestroy()
    }

    private fun initObserver() {
        membersViewModel.getAllMembersLiveData().observe(this, Observer {
            it?.let {
                if (it.members.isNotEmpty()) {
                    setupAdapter(it.members)
                } else {
                    rvMembers.visibility = View.GONE
                    txtNoMembers.visibility = View.VISIBLE
                }

                if (it.totalContribution > 0) {
                    txtTotalContribution.visibility = View.VISIBLE
                    txtTotalContribution.text =
                        getString(R.string.total_contribution) + " : " + it.totalContribution
                } else {
                    txtTotalContribution.visibility = View.INVISIBLE
                }
                if (it.totalExpense > 0) {
                    txtTotalExpanse.visibility = View.VISIBLE
                    txtTotalExpanse.text =
                        getString(R.string.total_expanse) + " : " + it.totalExpense
                } else {
                    txtTotalExpanse.visibility = View.INVISIBLE
                }
            }
        })

        membersViewModel.getErrorLiveData().observe(this, Observer {
            if (it) {
                Toast.makeText(this, getString(R.string.error_try_again), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupAdapter(it: List<Member>) {
        val adapter = MembersAdapter(it)
        rvMembers.layoutManager = LinearLayoutManager(this)
        rvMembers.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        )
        rvMembers.addItemDecoration(dividerItemDecoration)
    }

    private fun initDagger() {
        val activityComponent = DaggerMembersComponent.builder()
            .appModule((application as MyApplication).getAppModuleData())
            .build()
        activityComponent.inject(this)
    }

    override fun onClick(view: View) {
        val intent = Intent(this, AddMembersActivity::class.java)
        intent.putExtra(AddMembersActivity.KEY_TRIP_ID, tripId)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}
