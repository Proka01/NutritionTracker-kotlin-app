package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.FilteredMealResponse
import rs.raf.vezbe11.data.models.MealCategoryResponse
import rs.raf.vezbe11.data.models.MealResponse
import rs.raf.vezbe11.data.models.Resource

interface MealAPIRepository {
    fun fetchAllMealCategories(): Observable<Resource<List<MealCategoryResponse>>>
    fun fetchAllMealsByFirstLetter(letter : String) : Observable<Resource<List<MealResponse>>>

    fun fetchAllMealsByCategory(category : String) : Observable<Resource<List<MealResponse>>>

    fun fetchAllMealsByArea(area : String) : Observable<Resource<List<MealResponse>>>

    fun fetchAllMealsByMainIngredient(mainIngredient : String) : Observable<Resource<List<MealResponse>>>
}