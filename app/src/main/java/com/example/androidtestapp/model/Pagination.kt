package com.example.androidtestapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Pagination {

    @SerializedName("key")
    @Expose
    private var key: Any? = null

    fun getKey(): Any? {
        return key
    }

    fun setKey(key: Any?) {
        this.key = key
    }
}