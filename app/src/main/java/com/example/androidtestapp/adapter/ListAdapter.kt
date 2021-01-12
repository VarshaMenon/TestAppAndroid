package com.example.androidtestapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtestapp.R
import com.example.androidtestapp.model.Result
import com.example.androidtestapp.viewmodel.ListViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_classified_item.view.*
import java.util.*

class ListAdapter(
    private val items: List<Result?>,
    private val context: Context,
    private val listViewModel: ListViewModel
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.layout_classified_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.containerView.tvId.text = items[position]?.getName()?.capitalize(Locale.ENGLISH)
        holder.containerView.tvName.text = items[position]?.getPrice()

        Glide.with(context).load(items[position]?.getImageUrlsThumbnails()?.get(0)).placeholder(android.R.drawable.gallery_thumb)
            .into(holder.containerView.ivItem)
        holder.containerView.llContainer.setOnClickListener {
            listViewModel.singleItemMutableLiveData.value =
                items[position]
        }
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer { }
}