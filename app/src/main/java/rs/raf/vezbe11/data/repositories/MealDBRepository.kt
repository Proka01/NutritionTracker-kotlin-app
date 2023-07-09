package rs.raf.vezbe11.data.repositories

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.datasources.local.db.MealEntity

interface MealDBRepository {
    fun insertMeal(mealEntity: MealEntity): Completable

    fun insertMeals(mealEntities: List<MealEntity>): Single<List<Long>> // Moze da vrati i Completable

    fun getAllMeals(): Observable<List<MealEntity>>

    fun getMealById(id: Long): MealEntity

    fun update(mealEntity: MealEntity)

    fun updateMealNameById(id: Long, name: String): Completable

    fun delete(mealEntity: MealEntity)

    fun deleteById(id: Long)

    fun deleteAll(): Completable
}