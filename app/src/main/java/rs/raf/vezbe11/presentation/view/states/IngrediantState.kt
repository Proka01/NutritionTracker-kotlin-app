package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.IngredientResponse
import rs.raf.vezbe11.data.models.MealResponse

sealed class IngrediantState{
    object Loading: IngrediantState()
    object DataFetched: IngrediantState()
    data class Success(val ingredient: List<IngredientResponse>): IngrediantState()
    data class Error(val message: String): IngrediantState()
}
