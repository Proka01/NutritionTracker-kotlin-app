package rs.raf.vezbe11.presentation.view.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.view.activities.MealActivity
import rs.raf.vezbe11.presentation.view.activities.MealListActivity

class MealCardAdapter(private val cardsPerPage: Int) :
    RecyclerView.Adapter<MealCardAdapter.CardViewHolder>() {

    //added
    private var mealCardItems: MutableList<MealCardItem> = mutableListOf()
    var currentPage = 1

    fun updateItems(newCardItems: List<MealCardItem>) {
        mealCardItems.clear()
        mealCardItems.addAll(newCardItems)
        notifyDataSetChanged()
    }
    //

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.meal_card_item, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardItem = mealCardItems[position]
        holder.bind(cardItem)
    }

    override fun getItemCount(): Int {
        return mealCardItems.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.mealImageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.mealTitleTextView)

        fun bind(cardItem: MealCardItem) {
            titleTextView.text = cardItem.strMeal

            // Load image from URL using Glide
            Glide.with(itemView)
                .load(cardItem.strMealThumb)
                //.placeholder(R.drawable.placeholder) // Optional placeholder image
                //.error(R.drawable.error_image) // Optional error image
                .into(imageView)


            // Set click listener for the whole card
            itemView.setOnClickListener {
                // Handle card click
                // You can perform actions specific to the clicked card here
                val context = itemView.context
                val intent = Intent(context, MealActivity::class.java)
                println("CARD ITEM ID : " + cardItem.idMeal)
                intent.putExtra("id", cardItem.idMeal)
                context.startActivity(intent)
                //Toast.makeText(itemView.context, cardItem.strMeal, Toast.LENGTH_SHORT).show()
            }
        }
    }
}