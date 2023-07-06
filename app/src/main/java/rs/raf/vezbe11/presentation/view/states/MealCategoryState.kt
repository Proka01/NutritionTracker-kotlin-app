package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.MealCategoryResponse

sealed class MealCategoryState {
    object Loading: MealCategoryState()
    object DataFetched: MealCategoryState()
    data class Success(val mealCategories: List<MealCategoryResponse>): MealCategoryState()
    data class Error(val message: String): MealCategoryState()
}