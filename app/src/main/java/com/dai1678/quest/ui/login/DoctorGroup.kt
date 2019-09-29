package com.dai1678.quest.ui.login

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.dai1678.quest.App
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListItemDoctorBinding
import com.dai1678.quest.entity.Doctor2
import com.dai1678.quest.util.StringUtils
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.databinding.BindableItem

class DoctorGroup(private val doctor2: Doctor2) :
    BindableItem<ListItemDoctorBinding>(), StringUtils {

    private val application = App.instance

    override fun getLayout(): Int = R.layout.list_item_doctor

    override fun bind(viewBinding: ListItemDoctorBinding, position: Int) {
        viewBinding.doctorName = "${doctor2.lastName} ${doctor2.firstName}"
        viewBinding.lastLoginTime = getLastLoginTime(doctor2)

        viewBinding.listItemDoctorView.setOnClickListener {
            saveLoginDataToPref(doctor2)

            Snackbar.make(
                it,
                R.string.login_success_message,
                Snackbar.LENGTH_LONG
            ).apply {
                this.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                val snackBarText = view.findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_text
                )
                snackBarText.setTextColor(Color.WHITE)
            }.show()

            it.findNavController().navigate(R.id.action_loginFragment_to_patientListFragment)
        }
    }

    private fun getLastLoginTime(doctor2: Doctor2) : String {
        return if (doctor2.updatedAt.isEmpty()) {
            application.resources.getString(R.string.content_login_last_login_label)
        } else {
            doctor2.updatedAt.formatDateStr
        }
    }

    private fun saveLoginDataToPref(doctor2: Doctor2) {
        val preferences =
            application.getSharedPreferences(App.PREFERENCES_NAME, Context.MODE_PRIVATE)

        preferences.edit().apply {
            putString("doctorId", doctor2.id)
            apply()
        }
    }

}
