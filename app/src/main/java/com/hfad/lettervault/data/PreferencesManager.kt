package com.hfad.lettervault.data

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hfad.lettervault.SortOrder
import com.hfad.lettervault.data.PreferencesManager.PreferencesKeys.SORT_ORDER
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import com.hfad.lettervault.dataStore
import kotlinx.coroutines.flow.map


@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext val context: Context) {
    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
    }
    val preferencesFLow = context.dataStore.data
        .map { preferences ->
            val sortOrder = SortOrder.valueOf(
                preferences[SORT_ORDER] ?: SortOrder.BY_ALL.name
            )
            sortOrder
        }

//    To update existing data
    suspend fun updateSortOrder(sortOrder: SortOrder) {
        context.dataStore.edit { preferences ->
            preferences[SORT_ORDER] = sortOrder.name
        }
    }
}