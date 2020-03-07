package com.example.data.common

interface DataMapper<DATA, MODEL> {
    fun transform(data: DATA): MODEL
}