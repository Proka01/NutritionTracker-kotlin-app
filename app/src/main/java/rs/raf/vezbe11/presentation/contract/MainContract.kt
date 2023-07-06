package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.FilteredMealResponse
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.presentation.view.states.FilteredMealState
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.view.states.MealState

interface MainContract {

    interface ViewModel {

        val mealCategoryState: LiveData<MealCategoryState>
        val mealsByFirstLetterState: LiveData<MealState>
        val filteredMealsByCategoryState: LiveData<FilteredMealState>
        val filteredMealsByAreaState: LiveData<FilteredMealState>
        val filteredMealsByMainIngredientState: LiveData<FilteredMealState>

        fun fetchAllMealsByFirstLetter(letter : String)
        fun fetchAllMealCategories()
        fun fetchAllMealsByCategory(category : String)
        fun fetchAllMealsByArea(area : String)
        fun fetchAllMealsByMainIngredient(mainIngredient : String)
        fun printMealCategoryState() : String
        fun getMealCategoryListFromMealCategoryState() : List<MealCategory>
    }

}