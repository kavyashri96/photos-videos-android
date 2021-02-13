package com.robosoft.photosvideosapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.photoplayandroid.utils.setImage
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.data.model.Photo


class PhotosAdapter(private val itemClickListener: ((Pair<ACTION, Photo>) -> Unit)? = null) :
    RecyclerView.Adapter<PhotosAdapter.DataViewHolder>() {

    private var itemList: List<Photo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = itemList?.size ?: 0

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        itemList?.get(position)?.let {
            holder.bind(it)
        }
        setViewClickListener(holder, position)
    }

    private fun setViewClickListener(
        holder: DataViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(Pair(ACTION.CARD_CLICK, itemList!![position]))
        }
        holder.itemView.findViewById<ImageView>(R.id.favorite).setOnClickListener {
            itemClickListener?.invoke(Pair(ACTION.BOOKMARK, itemList!![position]))
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: Photo) {
            itemView.apply {
                findViewById<ImageView>(R.id.image).setImage(result.src.medium)
                findViewById<TextView>(R.id.textName).text  = result.photographer
            }
        }
    }

    fun addData(list: List<Photo>) {
        itemList = list
    }
}

enum class ACTION {
    BOOKMARK,
    CARD_CLICK
}
