package com.dai1678.quest.ui.questionnaire

//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.navArgs
//import com.dai1678.quest.R
//import kotlinx.android.synthetic.main.activity_questionnaire.*
//
//class QuestionnaireActivity : AppCompatActivity(R.layout.activity_questionnaire) {
//
//    private val args: QuestionnaireActivityArgs by navArgs()
//    private val viewModel: QuestionnaireViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        viewModel.setPatientId(args.patientId)
//
//        questionnaire_toolbar.apply {
//            inflateMenu(R.menu.questionnaire_menu)
//            setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.menu_to_suspend_check_dialog -> {
//                        findNavController(R.id.nav_questionnaire_host_fragment)
//                            .navigate(R.id.action_global_suspend_check_dialog)
//                    }
//                }
//                true
//            }
//        }
//    }
//}
