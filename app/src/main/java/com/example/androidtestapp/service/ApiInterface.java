package com.example.androidtestapp.service;

import com.example.androidtestapp.model.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("default/dynamodb-writer")
    Observable<ListResponse> getResponseList();
}
