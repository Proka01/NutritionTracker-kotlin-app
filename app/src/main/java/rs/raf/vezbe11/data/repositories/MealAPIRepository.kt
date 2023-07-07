package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.*

interface MealAPIRepository {
    fun fetchAllMealCategories(): Observable<Resource<List<MealCategoryResponse>>>
    fun fetchAllMealsByFirstLetter(letter : String) : Observable<Resource<List<MealResponse>>>

    fun fetchAllMealsByCategory(category : String) : Observable<Resource<List<MealResponse>>>

    fun fetchAllMealsByArea(area : String) : Observable<Resource<List<MealResponse>>>

    fun fetchAllMealsByMainIngredient(mainIngredient : String) : Observable<Resource<List<MealResponse>>>

    fun fetchMealsByName(name : String) : Observable<Resource<List<MealResponse>>>

    fun fetchAllAreas() : Observable<Resource<List<MealResponse>>>

    fun fetchAllIngredients() : Observable<Resource<List<IngredientResponse>>>
}