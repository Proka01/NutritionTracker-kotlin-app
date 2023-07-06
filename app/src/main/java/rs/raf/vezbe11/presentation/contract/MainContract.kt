package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.presentation.view.states.MealCategoryState

interface MainContract {

    interface ViewModel {

        val mealCategoryState: LiveData<MealCategoryState>
        fun fetchAllMealCategories()
        fun printMealCategoryState() : String
        fun getMealCategoryListFromMealCategoryState() : List<MealCategory>
    }

}