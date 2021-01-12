package com.example.androidtestapp

import android.os.Build
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.androidtestapp.view.detail.DetailActivity
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
class DetailActivityUnitTest {

    private var activity: DetailActivity? = null
    private var ivItem: ImageView? = null
    private var tvItemName: TextView? = null
    private var tvPrice: TextView? = null
    private var tvCreatedAt: TextView? = null
    private var progressBar: ProgressBar? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity =
            Robolectric.buildActivity(DetailActivity::class.java).create().start().resume().get()
        ivItem = activity?.findViewById(R.id.ivItem) as ImageView
        tvItemName = activity?.findViewById(R.id.tvItemName) as TextView
        tvPrice = activity?.findViewById(R.id.tvPrice) as TextView
        tvCreatedAt = activity?.findViewById(R.id.tvPrice) as TextView
        progressBar = activity?.findViewById(R.id.progressbar) as ProgressBar
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
        assertNotNull(ivItem)
        assertNotNull(tvItemName)
        assertNotNull(tvPrice)
        assertNotNull(tvCreatedAt)
        assertNotNull(progressBar)
    }
}