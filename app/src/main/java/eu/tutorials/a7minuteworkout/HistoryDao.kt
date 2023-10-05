package eu.tutorials.a7minuteworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query ("select * from `history-table`")
    fun getAllDates(): Flow<List<HistoryEntity>>
}