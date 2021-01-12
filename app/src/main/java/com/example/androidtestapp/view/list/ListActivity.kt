package com.example.androidtestapp.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtestapp.R
import com.example.androidtestapp.adapter.ListAdapter
import com.example.androidtestapp.model.Result
import com.example.androidtestapp.utils.Constants
import com.example.androidtestapp.view.detail.DetailActivity
import com.example.androidtestapp.viewmodel.ListViewModel
import com.example.androidtestapp.viewmodel.ListViewModelFactory
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setViewModelProvider()
    }

    private fun setViewModelProvider() {
        listViewModel = ViewModelProviders.of(this, ListViewModelFactory(this, application))
            .get(ListViewModel::class.java)
        listViewModel.setRecyclerViewItems()
        listViewModel.itemMutableLiveData.observe(this@ListActivity,
            Observer<List<Result?>> { items ->
                pbCircular.visibility = View.GONE
                rvUsers.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                rvUsers.adapter = ListAdapter(items, this@ListActivity, listViewModel)
                rvUsers?.findViewHolderForAdapterPosition(0)?.itemView?.performClick()
            })

        listViewModel.singleItemMutableLiveData.observe(this, Observer<Result> {
            val intent = Intent(this@ListActivity, DetailActivity::class.java)
            intent.putExtra(Constants.RESULT, it)
            startActivity(intent)
        })
    }
}