package rs.raf.vezbe11.data.datasources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.vezbe11.data.datasources.local.MealDao

@Database(
    entities = [MealEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MealDataBase : RoomDatabase() {
    // Getteri za sve DAO-e moraju biti navedeni ovde
    abstract fun getMealDao(): MealDao
}