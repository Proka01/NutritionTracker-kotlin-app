package rs.raf.vezbe11.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.databinding.LayoutItemMovieBinding

class MovieViewHolder(private val itemBinding: LayoutItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {
        itemBinding.titleTv.text = movie.title
    }

}