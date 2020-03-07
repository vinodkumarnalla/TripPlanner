package com.example.tripplanner.members.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.domain.models.MembersListChangeEvent
import com.example.tripplanner.MyApplication
import com.example.tripplanner.R
import com.example.tripplanner.members.di.DaggerMembersComponent
import com.example.tripplanner.members.viewmodel.MembersViewModel
import com.example.tripplanner.utils.RxBus
import kotlinx.android.synthetic.main.activity_add_members.*
import javax.inject.Inject

class AddMembersActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val KEY_TRIP_ID = "key.trip.id"
    }

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var rxBus: RxBus

    val membersViewModel: MembersViewModel by lazy {
        ViewModelProviders.of(this, modelFactory).get(MembersViewModel::class.java)
    }
    var tripId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_members)
        initDagger()
        initObserver()
        rxBus.register(this)
        btnAddMember.setOnClickListener(this)
        tripId = intent?.extras?.getLong(KEY_TRIP_ID) ?: 0
    }

    override fun onDestroy() {
        rxBus.unRegister(this)
        super.onDestroy()
    }

    private fun initObserver() {
        membersViewModel.getAddedMemberLiveDataObserver().observe(this, Observer {
            it?.let {
                Toast.makeText(this, getString(R.string.member_added), Toast.LENGTH_LONG).show()
                clearData()
                rxBus.send(MembersListChangeEvent())
            }
        })
        membersViewModel.getErrorLiveData().observe(this, Observer {
            if (it) {
                Toast.makeText(this, getString(R.string.error_try_again), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun clearData() {
        etMemberExpense.text.clear()
        etMemberContribution.text.clear()
        etMemberNumber.text.clear()
        etMemberName.text.clear()
    }

    private fun initDagger() {
        val activityComponent = DaggerMembersComponent.builder()
            .appModule((application as MyApplication).getAppModuleData())
            .build()
        activityComponent.inject(this)
    }

    override fun onClick(view: View?) {
        if (checkIsEmpty(etMemberName) || checkIsEmpty(etMemberContribution) || checkIsEmpty(
                etMemberExpense
            ) || checkIsEmpty(etMemberNumber)
        ) {
            Toast.makeText(this, getString(R.string.all_fields_required), Toast.LENGTH_LONG).show()
        } else {
            membersViewModel.addMember(
                getText(etMemberName),
                getText(etMemberNumber).toLong(),
                getText(etMemberContribution).toLong(),
                getText(etMemberExpense).toLong(),
                tripId
            )
        }
    }

    fun checkIsEmpty(et: EditText): Boolean {
        if (et.text.isEmpty()) {
            return true;
        }
        return false
    }

    fun getText(et: EditText): String {
        return et.text.trim().toString()
    }
}
