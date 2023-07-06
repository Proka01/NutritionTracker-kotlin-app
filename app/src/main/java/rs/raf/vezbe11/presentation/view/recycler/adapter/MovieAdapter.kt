package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.databinding.LayoutItemMovieBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.MovieDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MovieViewHolder

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding = LayoutItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}