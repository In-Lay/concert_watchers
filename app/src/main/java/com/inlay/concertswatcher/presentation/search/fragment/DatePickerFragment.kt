package com.inlay.concertswatcher.presentation.search.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.inlay.concertswatcher.databinding.FragmentDatePickerBinding
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFragment(private val onDatePicked: (String?, String?) -> Unit) : DialogFragment(),
    DialogOnClickFunctions {
    private lateinit var binding: FragmentDatePickerBinding
    private lateinit var dialog: Dialog
    private lateinit var calendar: Calendar
    private var startDate: String? = null
    private var endDate: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDatePickerBinding.inflate(layoutInflater)
        calendar = Calendar.getInstance()
        dialog = Dialog(requireContext())

        binding.vDatePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            null
        )
        binding.onClick = this

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(binding.root)

        return dialog
    }

    override fun onCancelClicked() {
        dialog.dismiss()
    }

    override fun onNextClicked() {
        var tempMinDate: Long
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        if (startDate == null) {
            binding.vDatePicker.let {
                calendar.set(it.year, it.month, it.dayOfMonth + 1)
                tempMinDate = calendar.timeInMillis
                calendar.set(it.year, it.month, it.dayOfMonth)
                startDate = simpleDateFormat.format(calendar.time)
            }
            binding.vDatePicker.minDate = tempMinDate
            //TODO Check
            binding.buttonOk.text = "OK"
        } else if (endDate == null) {
            binding.vDatePicker.let {
                calendar.set(it.year, it.month, it.dayOfMonth)
                endDate = simpleDateFormat.format(calendar.time)
            }
            onDatePicked(startDate, endDate)
            dialog.dismiss()
        }
    }
}
