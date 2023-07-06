package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.MealCategoryResponse
import rs.raf.vezbe11.data.models.Resource

interface MealCategoryRepository {
    fun fetchAllMealCategories(): Observable<Resource<List<MealCategoryResponse>>>
}