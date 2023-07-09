package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.datasources.local.MealDao
import rs.raf.vezbe11.data.datasources.local.db.MealEntity

class MealDBRepositoryImpl(private val mealDao: MealDao) : MealDBRepository {
    override fun insertMeal(mealEntity: MealEntity): Completable {
        return mealDao.insertMeal(mealEntity)
    }

    override fun insertMeals(mealEntity: List<MealEntity>): Single<List<Long>> {
        return mealDao.insertMeals(mealEntity)
    }

    override fun getAllMeals(): Observable<List<MealEntity>> {
        return mealDao
            .getAllMeals()
            .map {
                it.map {
                    MealEntity(
                        id = it.id,
                        mealName = it.mealName,
                        thumbnailURL = it.thumbnailURL,
                        instructions = it.instructions,
                        youtubeLink = it.youtubeLink,
                        ingredients = it.ingredients,
                        mealCategory = it.mealCategory,
                        mealType = it.mealType,
                        date = it.date
                    )
                }
            }
    }

    override fun getMealById(id: Long): MealEntity {
        return mealDao.getMealById(id)
    }

    override fun update(mealEntity: MealEntity) {
        return mealDao.update(mealEntity)
    }

    override fun updateMealNameById(id: Long, name: String): Completable {
        return Completable.fromCallable {
            val meal = mealDao.getMealById(id)
            val updatedMeal = meal.copy(
                mealName = name
            )
            mealDao.update(updatedMeal)
        }
    }

    override fun delete(mealEntity: MealEntity) {
        return mealDao.delete(mealEntity)
    }

    override fun deleteById(id: Long) {
        return mealDao.deleteById(id)
    }

    override fun deleteAll(): Completable {
        return mealDao.deleteAll()
    }
}