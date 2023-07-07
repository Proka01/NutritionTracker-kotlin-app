package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.vezbe11.data.models.FilteredMealWrapperResponse
import rs.raf.vezbe11.data.models.MealCategoryWrapperResponse
import rs.raf.vezbe11.data.models.MealWrapperResponse

interface MealAPIService {
    @GET("categories.php")
    fun getAllCategories(): Observable<MealCategoryWrapperResponse>

    @GET("search.php?")
    fun getAllMealsByFirstLetter(@Query("f") letter : String): Observable<MealWrapperResponse>

    @GET("filter.php?")
    fun getAllMealsByCategory(@Query("c") category : String): Observable<MealWrapperResponse>

    @GET("filter.php?")
    fun getAllMealsByArea(@Query("a") area : String): Observable<MealWrapperResponse>

    @GET("filter.php?")
    fun getAllMealsByMainIngredient(@Query("i") main_ingredient : String): Observable<MealWrapperResponse>
}