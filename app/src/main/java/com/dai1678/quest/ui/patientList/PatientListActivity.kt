package com.dai1678.quest.ui.patientList

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dai1678.quest.R
import com.dai1678.quest.ui.create.CreateUserActivity
import kotlinx.android.synthetic.main.activity_patient_list.*

class PatientListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_list)

        setSupportActionBar(patient_list_toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.patient_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_user_create -> {
                val intent = CreateUserActivity.createIntent(this)
                startActivity(intent)
                return true
            }
        }
        return false
    }

    companion object {
        fun createIntent(context: Context) = Intent(
            context,
            PatientListActivity::class.java
        )
    }
}
