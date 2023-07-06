package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.remote.MealAPIService
import rs.raf.vezbe11.data.models.FilteredMealResponse
import rs.raf.vezbe11.data.models.MealCategoryResponse
import rs.raf.vezbe11.data.models.MealResponse
import rs.raf.vezbe11.data.models.Resource

class MealAPIRepositoryImpl(
    private val remoteDataSource: MealAPIService
) : MealAPIRepository {

    override fun fetchAllMealCategories(): Observable<Resource<List<MealCategoryResponse>>> {
        return remoteDataSource
            .getAllCategories()
            .map {
                Resource.Success(it.categories)
            }
    }

    override fun fetchAllMealsByFirstLetter(letter: String): Observable<Resource<List<MealResponse>>> {
        return remoteDataSource
            .getAllMealsByFirstLetter(letter)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllMealsByCategory(category: String): Observable<Resource<List<FilteredMealResponse>>> {
        return remoteDataSource
            .getAllMealsByCategory(category)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllMealsByArea(area: String): Observable<Resource<List<FilteredMealResponse>>> {
        return remoteDataSource
            .getAllMealsByArea(area)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllMealsByMainIngredient(mainIngredient: String): Observable<Resource<List<FilteredMealResponse>>> {
        return remoteDataSource
            .getAllMealsByMainIngredient(mainIngredient)
            .map {
                Resource.Success(it.meals)
            }
    }

}