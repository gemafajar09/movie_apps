package com.gema.moviewapps.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gema.moviewapps.BuildConfig
import com.gema.moviewapps.data.remote.response.MoviewResponse
import com.gema.moviewapps.databinding.MovieItemBinding
import com.squareup.picasso.Picasso

class HomeAdapter(
    private val onItemClick: (MoviewResponse.ResultsItem) -> Unit
) : ListAdapter<MoviewResponse.ResultsItem, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviewResponse.ResultsItem) {
            binding.apply {
                tvTitle.text = data.title
                tvOverview.text = data.overview

                Picasso.get()
                    .load(BuildConfig.IMAGE_PATH+data.posterPath)
                    .into(ivPanel)

                itemView.setOnClickListener {
                    onItemClick(data)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<MoviewResponse.ResultsItem> =
        object : DiffUtil.ItemCallback<MoviewResponse.ResultsItem>() {
            override fun areItemsTheSame(
                oldItem: MoviewResponse.ResultsItem,
                newItem: MoviewResponse.ResultsItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: MoviewResponse.ResultsItem,
                newItem: MoviewResponse.ResultsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}