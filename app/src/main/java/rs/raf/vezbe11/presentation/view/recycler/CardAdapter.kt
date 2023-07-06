package rs.raf.vezbe11.presentation.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.R

class CardAdapter(private val cardItems: List<CardItem>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardItem = cardItems[position]
        holder.bind(cardItem)
    }

    override fun getItemCount(): Int {
        return cardItems.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val menuIcon: ImageView = itemView.findViewById(R.id.menuIcon)

        fun bind(cardItem: CardItem) {
            titleTextView.text = cardItem.title

            // Load image from URL using Glide
            Glide.with(itemView)
                .load(cardItem.imageUrl)
                //.placeholder(R.drawable.placeholder) // Optional placeholder image
                //.error(R.drawable.error_image) // Optional error image
                .into(imageView)

            // Set click listener for menu icon
            menuIcon.setOnClickListener {
                // Handle menu icon click
                // You can perform actions specific to the menu icon here
                Toast.makeText(itemView.context, cardItem.title, Toast.LENGTH_SHORT).show()
            }

            // Set click listener for the whole card
            itemView.setOnClickListener {
                // Handle card click
                // You can perform actions specific to the clicked card here
                Toast.makeText(itemView.context, cardItem.desc, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
