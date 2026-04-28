package com.stepone.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "daily_steps")
data class StepEntry(
    @PrimaryKey val date: String, // YYYY-MM-DD
    val steps: Int
)

@Dao
interface StepDao {
    @Query("SELECT * FROM daily_steps WHERE date = :date")
    fun getStepsForDate(date: String): Flow<StepEntry?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entry: StepEntry)
}

@Database(entities = [StepEntry::class], version = 1)
abstract class StepDatabase : RoomDatabase() {
    abstract fun stepDao(): StepDao
}
