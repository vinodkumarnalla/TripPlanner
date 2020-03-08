package com.example.tripplanner.members.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.common.Result
import com.example.domain.models.Member
import com.example.domain.models.Trip
import com.example.domain.models.TripDataModel
import com.example.domain.usecases.AddMemberUseCase
import com.example.domain.usecases.GetMembersUseCase
import javax.inject.Inject

class MembersViewModel @Inject constructor(
    val addMemberUseCase: AddMemberUseCase,
    val getMembersUseCase: GetMembersUseCase
) : ViewModel() {

    val memberMutableLiveData = MutableLiveData<Member>()
    val errorLiveData = MutableLiveData<Boolean>()
    val membersLiveData = MutableLiveData<TripDataModel>()

    fun addMember(name: String, number: Long, contribution: Long, expense: Long, tripId: Long) {

        val member = Member(1, name, number, contribution, expense, tripId)
        addMemberUseCase.execute(member).subscribe() {
            when (it) {
                is Result.Success -> {
                    errorLiveData.value = false
                    memberMutableLiveData.value = member
                }
                is Result.Failure -> {
                    errorLiveData.value = true
                }
            }
        }

    }


    fun getAllMembers(tripId: Long) {
        getMembersUseCase.execute(tripId).subscribe() {
            when (it) {
                is Result.Success -> {
                    errorLiveData.value = false
                    membersLiveData.value = it.value
                }
                is Result.Failure -> {
                    errorLiveData.value = true
                }
            }
        }
    }

    fun getAddedMemberLiveDataObserver(): LiveData<Member> {
        return memberMutableLiveData
    }

    fun getErrorLiveData(): LiveData<Boolean> {
        return errorLiveData
    }

    fun getAllMembersLiveData(): LiveData<TripDataModel> {
        return membersLiveData
    }

}