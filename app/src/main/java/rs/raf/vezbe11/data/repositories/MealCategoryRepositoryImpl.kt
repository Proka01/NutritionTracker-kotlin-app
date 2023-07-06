package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.remote.MealCategoryService
import rs.raf.vezbe11.data.models.MealCategoryResponse
import rs.raf.vezbe11.data.models.Resource

class MealCategoryRepositoryImpl(
    private val remoteDataSource: MealCategoryService
) : MealCategoryRepository {

    override fun fetchAllMealCategories(): Observable<Resource<List<MealCategoryResponse>>> {
        return remoteDataSource
            .getAll()
            .map {
                Resource.Success(it.categories)
            }
    }

}