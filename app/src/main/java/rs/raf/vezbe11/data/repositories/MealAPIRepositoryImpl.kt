package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.remote.MealAPIService
import rs.raf.vezbe11.data.models.*

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

    override fun fetchAllMealsByCategory(category: String): Observable<Resource<List<MealResponse>>> {
        return remoteDataSource
            .getAllMealsByCategory(category)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllMealsByArea(area: String): Observable<Resource<List<MealResponse>>> {
        return remoteDataSource
            .getAllMealsByArea(area)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllMealsByMainIngredient(mainIngredient: String): Observable<Resource<List<MealResponse>>> {
        return remoteDataSource
            .getAllMealsByMainIngredient(mainIngredient)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchMealsByName(name: String): Observable<Resource<List<MealResponse>>> {
        return remoteDataSource
            .getMealsByName(name)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllAreas(): Observable<Resource<List<MealResponse>>> {
        return remoteDataSource
            .getAllAreas()
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllIngredients(): Observable<Resource<List<IngredientResponse>>> {
        return remoteDataSource
            .getAllIngredients()
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchMealById(id : String): Observable<Resource<List<MealResponse>>> {
        return remoteDataSource
            .getMealById(id)
            .map {
                Resource.Success(it.meals)
            }
    }

}