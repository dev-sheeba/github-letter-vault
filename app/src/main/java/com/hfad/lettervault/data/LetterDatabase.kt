package com.hfad.lettervault.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hfad.lettervault.Converters
import com.hfad.lettervault.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Letter::class], version = 4)
@TypeConverters(Converters::class)
abstract class LetterDatabase : RoomDatabase() {

    abstract fun letterDao(): LetterDao

    class LetterDatabaseCallback @Inject constructor(
        private val database: Provider<LetterDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = database.get().letterDao()

            applicationScope.launch {
                dao.upsert(Letter("Android","Android is a mobile operating system based on a modified version of the Linux kernel and other open source software, designed primarily for touchscreen mobile devices such as smartphones and tablets."))
                dao.upsert(Letter("Apple","Apple Inc. is an American multinational technology company that specializes in consumer electronics,"))
                dao.upsert(Letter("Mango","A mango is an edible stone fruit produced by the tropical tree Mangifera indica which is believed to have originated from the region between northwestern Myanmar, Bangladesh, and northeastern India."))
                dao.upsert(Letter("Women","Dresses and jumpsuits - Bags - Women's shirts - Shoes - Jeans"))
                dao.upsert(Letter("Men","Shirts - Polos - Jackets - Trousers - Shoes - Jeans - Sweatshirts"))
            }
        }

    }

}