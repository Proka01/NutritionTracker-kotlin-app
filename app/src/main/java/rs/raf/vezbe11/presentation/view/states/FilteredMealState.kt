package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.FilteredMealResponse
import rs.raf.vezbe11.data.models.MealResponse

sealed class FilteredMealState{
    object Loading: FilteredMealState()
    object DataFetched: FilteredMealState()
    data class Success(val meals: List<FilteredMealResponse>): FilteredMealState()
    data class Error(val message: String): FilteredMealState()
}
