package com.purevpn.database

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.purevpn.database.dao.UserDao
import com.purevpn.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao


    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    val databaseCreated: LiveData<Boolean>
        get() = mIsDatabaseCreated

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    companion object {
        private var sInstance: ApplicationDatabase? = null
        @VisibleForTesting
        val DATABASE_NAME = "users.db"
        private val MIGRATION_0_1: Migration = object : Migration(0, 1) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("Migration", "Done")
            }
        }


        fun getInstance(context: Context): ApplicationDatabase? {
            if (sInstance == null) {
                synchronized(ApplicationDatabase::class.java) {
                    if (sInstance == null) {
                        sInstance = buildDatabase(context.applicationContext)
                        sInstance!!.updateDatabaseCreated(context.applicationContext)
                    }
                }
            }
            return sInstance
        }

        /**
         * Build the database. [Builder.build] only sets up the database configuration and
         * creates a new instance of the database.
         * The SQLite database is only created when it's accessed for the first time.
         */
        private fun buildDatabase(appContext: Context): ApplicationDatabase {
            return Room.databaseBuilder(appContext, ApplicationDatabase::class.java, DATABASE_NAME)
                .addMigrations(MIGRATION_0_1)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        val database = ApplicationDatabase.getInstance(appContext)
                        getInstance(appContext)?.setDatabaseCreated()
                    }
                })
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        getInstance(appContext)?.setDatabaseCreated()
                    }
                }).build()
        }
    }


    /**
     * Check whether the database already exists and expose it via [.getDatabaseCreated]
     */
    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }
}