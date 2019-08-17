package com.dai1678.quest.ui.questionnaire

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.dai1678.quest.R
import kotlinx.android.synthetic.main.activity_questionnaire.*

class QuestionnaireActivity : AppCompatActivity() {

    private val args: QuestionnaireActivityArgs by navArgs()
    lateinit var patientId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        setSupportActionBar(questionnaire_toolbar)

        patientId = args.patientId
        Log.d("patientId", args.patientId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.questionnaire_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_to_suspend_check_dialog -> {
                findNavController(R.id.nav_questionnaire_host_fragment)
                    .navigate(R.id.action_global_suspend_check_dialog)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val INTENT_QUESTIONNAIRE_ACTIVITY_KEY = "QUESTIONNAIRE_ACTIVITY_KEY"

        fun createIntent(
            context: Context,
            patientId: String
        ): Intent {
            val intent = Intent(context, QuestionnaireActivity::class.java)
            intent.putExtra(INTENT_QUESTIONNAIRE_ACTIVITY_KEY, patientId)
            return intent
        }
    }
}
