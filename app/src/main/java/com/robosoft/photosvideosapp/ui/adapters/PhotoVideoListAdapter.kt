package com.robosoft.photosvideosapp.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.photosvideosapp.utils.setImage
import com.robosoft.photosvideosapp.R
import com.robosoft.photosvideosapp.data.model.Photo
import com.robosoft.photosvideosapp.data.model.Src
import com.robosoft.photosvideosapp.utils.ScreenUtils


class PhotosAdapter(
    private val itemClickListener: ((Pair<ACTION, Photo>) -> Unit)? = null,
    private val screenSize: ScreenUtils.ScreenSize
) :
    RecyclerView.Adapter<PhotosAdapter.DataViewHolder>() {

    private val itemList: ArrayList<Photo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return DataViewHolder(view, screenSize)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList[position])
        setViewClickListener(holder, position)
    }

    private fun setViewClickListener(
        holder: DataViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(Pair(ACTION.CARD_CLICK, itemList[position]))
        }
        holder.itemView.findViewById<ImageView>(R.id.favorite).setOnClickListener {
            itemClickListener?.invoke(Pair(ACTION.BOOKMARK, itemList[position]))
        }
    }

    class DataViewHolder(itemView: View, private val screenSize: ScreenUtils.ScreenSize) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(result: Photo) {
            itemView.apply {
                findViewById<ImageView>(R.id.image).setImage(getImage(result.src))
                findViewById<TextView>(R.id.textName).text = result.photographer
            }
        }

        private fun getImage(source: Src): String {
            return when (screenSize) {
                ScreenUtils.ScreenSize.NORMAL -> source.medium
                ScreenUtils.ScreenSize.SMALL -> source.small
                ScreenUtils.ScreenSize.LARGE -> source.large
                ScreenUtils.ScreenSize.X_LARGE -> source.large2x
                ScreenUtils.ScreenSize.LANDSCAPE -> source.landscape
            }
        }
    }

    fun addData(list: List<Photo>) {
        itemList.addAll(list)
    }

    fun delete(photo: Photo) {
        itemList.remove(photo)
        notifyDataSetChanged()
    }
}

enum class ACTION {
    BOOKMARK,
    CARD_CLICK
}
