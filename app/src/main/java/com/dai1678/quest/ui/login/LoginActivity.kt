package com.dai1678.quest.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dai1678.quest.R
import com.dai1678.quest.ui.patientList.PatientListActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //TODO tokenがあればPatientListActivityへIntent
    }
}
