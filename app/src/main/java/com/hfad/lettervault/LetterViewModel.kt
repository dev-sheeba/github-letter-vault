package com.hfad.lettervault

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.hfad.lettervault.data.Letter
import com.hfad.lettervault.data.LetterDao
import com.hfad.lettervault.data.LetterRepository
import com.hfad.lettervault.data.PreferencesManager
import com.hfad.lettervault.notification.cancelNotifications
import com.hfad.lettervault.notification.sendNotification
import com.hfad.lettervault.receiver.AlarmReceiver
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.content.Context as Context1

@HiltViewModel
class LetterViewModel @Inject constructor(
    private val letterRepository: LetterRepository,
    private val preferencesManager: PreferencesManager,
    private val app: Application,
) : ViewModel() {

    private val REQUEST_CODE = 0
//    private val notifyPendingIntent: PendingIntent

    private val preferencesFlow = preferencesManager.preferencesFLow
    private val alarmManager = app.getSystemService(android.content.Context.ALARM_SERVICE) as AlarmManager
    private val notifyIntent = Intent(app, AlarmReceiver::class.java)

//    private var _alarmOn = MutableLiveData<Boolean>()
//    val isAlarmOn: LiveData<Boolean>
//        get() = _alarmOn
//
    init {
//        _alarmOn.value = PendingIntent.getBroadcast(
//            app,
//            REQUEST_CODE,
//            notifyIntent,
//            PendingIntent.FLAG_NO_CREATE
//        ) != null

//        notifyPendingIntent = PendingIntent.getBroadcast(
//            app,
//            REQUEST_CODE,
//            notifyIntent,
//            PendingIntent.FLAG_NO_CREATE
//        )
    }

    val letters = preferencesFlow.asLiveData().switchMap { f ->
        when (f) {
            SortOrder.BY_ALL -> letterRepository.allLetters.asLiveData()
            SortOrder.BY_RECENT -> letterRepository.getLetterSortedByRecent.asLiveData()
            SortOrder.BY_REMINDER -> letterRepository.getLetterSortedByReminder.asLiveData()
        }
    }

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun upsert(letter: Letter) = viewModelScope.launch {
        val triggerTime = SystemClock.elapsedRealtime() + DateUtils.MINUTE_IN_MILLIS
        val notificationManager = ContextCompat.getSystemService(
            app,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification("time is running", app)
//        notificationManager.cancelNotifications()

//        AlarmManagerCompat.setExactAndAllowWhileIdle(
//            alarmManager,
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            triggerTime,
//            notifyPendingIntent
//        )
        letterRepository.upsert(letter)
    }
}

//class LetterViewModelFactory(private val repository: LetterRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LetterViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return LetterViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}


//class LetterViewModel @Inject constructor (private val letterDao: LetterDao): ViewModel() {
//
//}
//val sortOrder = MutableStateFlow(SortOrder.BY_ALL)

