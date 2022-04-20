package com.kareemdev.tmdbapp.presentation.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kareemdev.tmdbapp.R
import com.kareemdev.tmdbapp.core.domain.model.Movie
import com.kareemdev.tmdbapp.databinding.ItemListMovieBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {
    private var listData = ArrayList<Movie>()
    var onItemClick:((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie){
            with(binding){
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.baseUrlImage, data.posterPath))
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.overview
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}