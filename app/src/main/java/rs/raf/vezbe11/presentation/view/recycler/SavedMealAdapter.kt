package rs.raf.vezbe11.presentation.view.recycler

import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.datasources.local.db.MealEntity
import rs.raf.vezbe11.presentation.view.activities.MealListActivity

class SavedMealAdapter(private val mealEntities: List<MealEntity>) :
    RecyclerView.Adapter<SavedMealAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.saved_meal_card, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val mealEntityItem = mealEntities[position]
        holder.bind(mealEntityItem)
    }

    override fun getItemCount(): Int {
        return mealEntities.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewSaved)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextViewSaved)
        private val editIcon: ImageView = itemView.findViewById(R.id.menuIconSaved)

        fun bind(mealEntityItem: MealEntity) {
            titleTextView.text = mealEntityItem.mealName
            var imageString = mealEntityItem.thumbnailURL

            if (mealEntityItem.thumbnailURL!!.contains("https://www.themealdb")) {

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

            editIcon.setOnClickListener {

            }

            // Set click listener for the whole card
            itemView.setOnClickListener {
                // Handle card click
                // You can perform actions specific to the clicked card here
                Toast.makeText(itemView.context, mealEntityItem.mealName, Toast.LENGTH_SHORT).show()
//                val context = itemView.context
//                val intent = Intent(context, MealListActivity::class.java)
//                intent.putExtra("message", mealEntityItem.mealName)
//                context.startActivity(intent)
            }
        }
    }
}