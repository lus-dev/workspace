package lus.areapass.pass

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import org.threeten.bp.LocalDate

class DatePickerFragment(
    private val observable: MutableLiveData<String>
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val currentDate = LocalDate.now()
        return DatePickerDialog(requireContext(), this, currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        observable.value = "$dayOfMonth.$month.$year"
    }

    fun show(manager: FragmentManager) {
        super.show(manager, this::class.simpleName)
    }

}