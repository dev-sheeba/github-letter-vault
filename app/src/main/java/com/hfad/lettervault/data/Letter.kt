package com.hfad.lettervault.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import android.provider.SyncStateContract
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "letter_table")
@Parcelize
data class Letter(
    var title: String,
    var subTitle:String,
    var lastViewed: Long? = System.currentTimeMillis(),
    var reminder: Long? = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    var letterId: Int = 0,
    @TypeConverters()
    var created: Date? = null,
    @TypeConverters()
    var expiryDate: Date? = null

): Parcelable {
    val createdDateFormatted: String
    get() = DateFormat.getDateTimeInstance().format(created)
}

