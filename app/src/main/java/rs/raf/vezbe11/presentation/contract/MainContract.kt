package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.db.MealEntity
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.presentation.view.recycler.MealCardItem
import rs.raf.vezbe11.presentation.view.states.IngrediantState
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.view.states.MealDBState
import rs.raf.vezbe11.presentation.view.states.MealState

interface MainContract {

    interface ViewModel {

        val mealCategoryState: LiveData<MealCategoryState>
        val mealsByFirstLetterState: LiveData<MealState>
        val filteredMealsByCategoryState: LiveData<MealState>
        val filteredMealsByAreaState: LiveData<MealState>
        val filteredMealsByMainIngredientState: LiveData<MealState>
        val filteredMealsByNameState: LiveData<MealState>

        val areaState : LiveData<MealState>
        val ingredientState : LiveData<IngrediantState>
        val fetchedMealById : LiveData<MealState>

        //val readMealsFromDB: LiveData<MealDBState>

        //Meal API
        fun fetchAllMealsByFirstLetter(letter : String)
        fun fetchAllMealCategories()
        fun fetchAllMealsByCategory(category : String)
        fun fetchAllMealsByArea(area : String)
        fun fetchAllMealsByMainIngredient(mainIngredient : String)
        fun fetchMealsByName(name : String)
        fun fetchAllAreas()
        fun fetchAllIngredients()
        fun fetchMealById(id : String)

        //Helper functions
        fun printMealCategoryState() : String
        fun getMealCategoryListFromMealCategoryState() : List<MealCategory>
        fun getMealCardItemListFromMealState(mealStateList: LiveData<MealState>) : List<MealCardItem>
        fun getArrayOfCategories() : Array<String?>
        fun getArrayOfAreas() : Array<String?>
        fun getArrayOfIngredients() : Array<String?>
        fun getFetchedMeal(): Meal?
        //fun getReadMealsFromDB() : List<MealEntity>

        //Meal DB
        fun insertMeal(mealEntity: MealEntity)
        fun insertMeals(mealEntities: List<MealEntity>)
        fun getAllMeals()
    }

}