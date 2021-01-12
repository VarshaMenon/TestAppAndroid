package com.example.androidtestapp.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androidtestapp.model.ListResponse
import com.example.androidtestapp.model.Result
import com.example.androidtestapp.service.ApiClient
import com.example.androidtestapp.service.ApiInterface
import com.example.androidtestapp.utils.CommonMethods
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListViewModel(private val context: Context, application: Application) :
    AndroidViewModel(application) {

    val itemMutableLiveData = MutableLiveData<List<Result?>>()
    val singleItemMutableLiveData = MutableLiveData<Result>()
    private val commonMethods = CommonMethods()

    fun setRecyclerViewItems() {
        val apiInterface =
            ApiClient.SampleObject.getInstance()
                .getRetrofit(context, commonMethods.isInternetConnected(context))
                .create(ApiInterface::class.java)
        apiInterface.responseList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ListResponse> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ListResponse) {
                    itemMutableLiveData.value = t.getResults()
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
