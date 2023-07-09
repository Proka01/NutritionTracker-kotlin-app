package rs.raf.vezbe11.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.datasources.local.db.MealEntity

@Dao
abstract class MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(mealEntity: MealEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeals(mealEntities: List<MealEntity>): Single<List<Long>> // Moze da vrati i Completable

    @Query("SELECT * FROM meals")
    abstract fun getAllMeals(): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE id == :id")
    abstract fun getMealById(id: Long): MealEntity

    // Pogledati komentar za delete metodu
    @Update
    abstract fun update(mealEntity: MealEntity)

    // Pogledati komentar za deleteById metodu
    @Query("UPDATE meals SET mealName = :name WHERE id == :id")
    abstract fun updateMealNameById(id: Long, name: String): Completable

    @Delete
    abstract fun delete(mealEntity: MealEntity) : Completable

    // Ova metoda se manje uklapa u ceo koncept kada radimo sa ORM-om, ali radi
    // identicnu stvar kao metoda iznad
    @Query("DELETE FROM meals WHERE id == :id")
    abstract fun deleteById(id: Long)

    @Query("DELETE FROM meals")
    abstract fun deleteAll(): Completable
}