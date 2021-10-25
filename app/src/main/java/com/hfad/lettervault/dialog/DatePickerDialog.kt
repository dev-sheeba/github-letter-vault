package com.hfad.lettervault.dialog


import androidx.fragment.app.DialogFragment
import java.util.*
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import java.text.SimpleDateFormat
import kotlin.math.min


open class DatePickerFragmentDialog(private val listener: OnExpiryDateSelectedListener) : DialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    interface OnExpiryDateSelectedListener{
        fun onExpiryDateSelected(expiryDate: Date)
        fun onExpiryTimeSelected(expiryTime: Long)
    }

    private val calendar = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val hour = 0
        val minute = 0
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        TimePickerDialog(activity, this, hour, minute, true).show()
        listener.onExpiryDateSelected(calendar.time)

    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        calendar.set(Calendar.HOUR, hour)
        calendar.set(Calendar.MINUTE, minute)

        listener.onExpiryTimeSelected(calendar.timeInMillis)

    }
}