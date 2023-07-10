package rs.raf.vezbe11.presentation.view.recycler

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.DailyPlan
import rs.raf.vezbe11.presentation.view.activities.MealActivity
import java.time.DayOfWeek

class DailyPlannerCardAdapter(private var dayOfTheWeek: String) :
    RecyclerView.Adapter<DailyPlannerCardAdapter.CardViewHolder>() {

    //added
    private var mealCardItems: MutableList<MealCardItem> = mutableListOf()
    private lateinit var dailyPlan: DailyPlan

    fun updateDayOfTheWeek(newDay: String)
    {
        dayOfTheWeek = newDay
    }

    fun updateDailyPlan(newDailyPlan: DailyPlan)
    {
        dailyPlan = newDailyPlan
    }

    fun getDailyPlan() : DailyPlan
    {
        return dailyPlan
    }

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
            var imageString = cardItem.strMealThumb

            if (cardItem.strMealThumb!!.contains("https://www.themealdb")) {

                Glide.with(itemView)
                    .load(imageString)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                    .into(imageView)

            } else {
                // Assuming you have the Base64 encoded image string in the variable "imageString"
                val decodedByteArray = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT)
                val decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)

                // Load image from URL using Glide
                Glide.with(itemView)
                    .load(decodedBitmap)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                    //.error(R.drawable.error_image) // Optional error image
                    .into(imageView)
            }


            // Set click listener for the whole card
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, dayOfTheWeek + ":" + cardItem.strMeal, Toast.LENGTH_SHORT).show()
            }
        }
    }
}