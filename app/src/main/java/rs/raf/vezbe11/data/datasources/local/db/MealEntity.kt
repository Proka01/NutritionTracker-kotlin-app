package rs.raf.vezbe11.data.datasources.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var mealName: String?,
    var thumbnailURL: String?,
    var instructions: String?,
    var youtubeLink: String?,
    var ingredients: String?,
    var mealCategory: String?, //Beef, Seafood, ...
    var mealType: String?, // Breakfast, Lunch, Dinner, Snack
    var date: Date = Date()
) : java.io.Serializable
