package com.example.nvgtest.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.nvgtest.R
import com.example.nvgtest.data.model.response.Repository
import kotlinx.android.synthetic.main.adapter_repository_item.view.*


class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder>() {
    var repositories: MutableList<Repository> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_repository_item, parent, false)
        return RepositoryHolder(view)
    }

    override fun getItemCount(): Int = if (repositories != null) repositories.size else 0

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        holder.renderView(repositories.get(position), position)
    }


    inner class RepositoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun renderView(repository: Repository, position: Int) {
            itemView.tvName.setText(repository.name)
            itemView.tvDescription.setText(repository.description)
            itemView.tvLanguage.setText(repository.language)
            itemView.tvUpdateAt.setText(repository.updatedAt)
            itemView.tvStar.setText("${repository.stargazersCount}")
            itemView.tvWatches.setText("${repository.watchersCount}")
            itemView.tvFolk.setText("${repository.forksCount}")

//            if (repository.pageNumber!=0) {
//                itemView.header.visibility = View.VISIBLE
//                itemView.tvNumHeader.setText("${repository.pageNumber}")
//            }else itemView.header.visibility = View.GONE

            if (position % 2 == 0) itemView.layoutContent.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.layoutContent.context,
                    R.color.white
                )
            )
            else itemView.layoutContent.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.layoutContent.context,
                    R.color.gray_height
                )
            )
            itemView.setOnClickListener{
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(repository.link))
                itemView.context.startActivity(browserIntent)
            }
        }

    }
}