package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.vezbe11.data.models.MealCategoryResponse
import rs.raf.vezbe11.data.models.MealCategoryWrapperResponse

interface MealCategoryService {
    @GET("categories.php")
    fun getAll(): Observable<MealCategoryWrapperResponse>
}