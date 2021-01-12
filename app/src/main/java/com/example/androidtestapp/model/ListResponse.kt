package com.example.androidtestapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ListResponse {

    @SerializedName("results")
    @Expose
    private var results: List<Result?>? = null

    @SerializedName("pagination")
    @Expose
    private var pagination: Pagination? = null

    fun getResults(): List<Result?>? {
        return results
    }

    fun setResults(results: List<Result?>?) {
        this.results = results
    }

    fun getPagination(): Pagination? {
        return pagination
    }

    fun setPagination(pagination: Pagination?) {
        this.pagination = pagination
    }
}