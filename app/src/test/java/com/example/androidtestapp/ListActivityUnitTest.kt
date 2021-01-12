package com.example.androidtestapp

import android.os.Build
import com.example.androidtestapp.model.ListResponse
import com.example.androidtestapp.model.Result
import com.example.androidtestapp.service.ApiClient
import com.example.androidtestapp.service.ApiInterface
import com.example.androidtestapp.utils.CommonMethods
import com.example.androidtestapp.view.list.ListActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class ListActivityUnitTest {
    private var activity: ListActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity =
            Robolectric.buildActivity(ListActivity::class.java).create().start().resume().get()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
        assertNotNull(activity?.rvUsers)
    }

    /*@Test
    fun shouldStartNextActivityWhenListItemClicked() {
        activity?.rvUsers?.findViewHolderForAdapterPosition(0)?.itemView?.performClick()

        val expectedIntent = Intent(activity, DetailActivity::class.java)
        val shadowActivity: ShadowActivity = shadowOf(activity)
        val actualIntent = shadowActivity.nextStartedActivity
        TestCase.assertTrue(actualIntent.filterEquals(expectedIntent))
    }*/

    @Test
    fun testListingApi() {
        val apiInterface =
            activity?.let {
                ApiClient.SampleObject.getInstance().getRetrofit(it,CommonMethods().isInternetConnected(
                    activity!!
                )).create(
                    ApiInterface::class.java)
            }
        apiInterface?.responseList
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : Observer<ListResponse> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ListResponse) {
                    assertNotNull(t.getResults())
                    assert(t.getResults()?.get(0) is Result)
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}