package rs.raf.vezbe11.data.models

import java.io.Serializable

class Meal (
    val idMeal: String?,
    val strMeal: String?,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    var strMealThumb: String?,
    val strTags: String?,
    val strYoutube: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?
) : Serializable {
    fun getAllIngredients(): List<String> {
        val ingredients = mutableListOf<String>()
        this.apply {
            if (!strIngredient1.isNullOrEmpty()) ingredients.add(strIngredient1)
            if (!strIngredient2.isNullOrEmpty()) ingredients.add(strIngredient2)
            if (!strIngredient3.isNullOrEmpty()) ingredients.add(strIngredient3)
            if (!strIngredient4.isNullOrEmpty()) ingredients.add(strIngredient4)
            if (!strIngredient5.isNullOrEmpty()) ingredients.add(strIngredient5)
            if (!strIngredient6.isNullOrEmpty()) ingredients.add(strIngredient6)
            if (!strIngredient7.isNullOrEmpty()) ingredients.add(strIngredient7)
            if (!strIngredient8.isNullOrEmpty()) ingredients.add(strIngredient8)
            if (!strIngredient9.isNullOrEmpty()) ingredients.add(strIngredient9)
            if (!strIngredient10.isNullOrEmpty()) ingredients.add(strIngredient10)
            if (!strIngredient11.isNullOrEmpty()) ingredients.add(strIngredient11)
            if (!strIngredient12.isNullOrEmpty()) ingredients.add(strIngredient12)
            if (!strIngredient13.isNullOrEmpty()) ingredients.add(strIngredient13)
            if (!strIngredient14.isNullOrEmpty()) ingredients.add(strIngredient14)
            if (!strIngredient15.isNullOrEmpty()) ingredients.add(strIngredient15)
            if (!strIngredient16.isNullOrEmpty()) ingredients.add(strIngredient16)
            if (!strIngredient17.isNullOrEmpty()) ingredients.add(strIngredient17)
            if (!strIngredient18.isNullOrEmpty()) ingredients.add(strIngredient18)
            if (!strIngredient19.isNullOrEmpty()) ingredients.add(strIngredient19)
            if (!strIngredient20.isNullOrEmpty()) ingredients.add(strIngredient20)
        }
        return ingredients
    }

    fun concatenateStrings(strings: List<String>): String {
        return strings.joinToString(", ")
    }
}