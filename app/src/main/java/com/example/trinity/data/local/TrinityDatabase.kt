package com.example.trinity.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        HomeworkEntity::class,
        NoticeEntity::class,
        TimetableEntity::class,
        StudyMaterialEntity::class,
        AssignmentEntity::class,
        ExamScheduleEntity::class,
        UserPreferenceEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TrinityDatabase : RoomDatabase() {

    abstract fun trinityDao(): TrinityDao

    companion object {
        @Volatile
        private var INSTANCE: TrinityDatabase? = null

        fun getDatabase(context: Context): TrinityDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrinityDatabase::class.java,
                    "trinity_grammar_school.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
