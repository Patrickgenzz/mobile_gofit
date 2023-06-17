package com.example.mobile_gofit.utils

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.text.NumberFormat
import java.util.*

class Format {
    companion object {
        @JvmStatic
        fun formatDateTime(dateString: String?): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy 'Jam' HH:mm", Locale("id", "ID"))

            val date = inputFormat.parse(dateString)
            return outputFormat.format(date)
        }

        @JvmStatic
        fun reverseFormatDateTime(dateString: String?): String {
            return if(dateString == "Pilih Tanggal"){
                "Pilih Tanggal"
            }else{
                val inputFormat = SimpleDateFormat("dd MMMM yyyy 'Jam' HH:mm", Locale("id", "ID"))
                val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

                val date = inputFormat.parse(dateString)
                outputFormat.format(date)
            }
        }

        fun formatDate(dateString: String?): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

            val date = inputFormat.parse(dateString)
            return outputFormat.format(date)
        }

        @JvmStatic
        fun formatRupiah(amountString: String?): String {
            val amount = amountString?.toLongOrNull() ?: 0

            val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            format.currency = Currency.getInstance("IDR")

            return format.format(amount)
        }

        @JvmStatic
        fun dateToString(year: Int, month: Int, day: Int, hourOfDay: Int, minute: Int): String {
            val formattedYear = year.toString().padStart(4, '0')
            val formattedMonth = (month + 1).toString().padStart(2, '0')
            val formattedDay = day.toString().padStart(2, '0')
            val formattedHour = hourOfDay.toString().padStart(2, '0')
            val formattedMinute = minute.toString().padStart(2, '0')

            return "$formattedYear-$formattedMonth-$formattedDay $formattedHour:$formattedMinute"
        }

        @JvmStatic
        fun timeToString(hourOfDay: Int, minute: Int): String {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)

            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            return dateFormat.format(calendar.time)
        }
    }
}