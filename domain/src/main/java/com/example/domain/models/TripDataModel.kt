package com.example.domain.models

data class TripDataModel(
    var members: List<Member>,
    val totalExpense: Long,
    val totalContribution: Long
)