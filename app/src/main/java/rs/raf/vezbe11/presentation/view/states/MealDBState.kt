package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.datasources.local.db.MealEntity
import rs.raf.vezbe11.data.models.MealResponse

sealed class MealDBState{
    object Loading: MealDBState()
    object DataFetched: MealDBState()
    data class Success(val mealsDB: List<MealEntity>): MealDBState()
    data class Error(val message: String): MealDBState()
}
