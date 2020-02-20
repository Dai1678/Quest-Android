package com.dai1678.quest.ui.questionnaire

import androidx.lifecycle.ViewModel
import com.dai1678.quest.model.PatientDetail
import com.dai1678.quest.model.ScreenLog
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

class QuestionnairePagerViewModel : ViewModel() {

    private val fireBaseDatabase = FirebaseDatabase.getInstance()

    private var scrollCount = 0

    fun sendScreenLog(page: Int, patientDetail: PatientDetail) {

        scrollCount++

        val screenLog = ScreenLog(
            patientId = patientDetail.id,
            currentPage = page,
            currentTime = Date().time,
            scrollCount = scrollCount,
            gender = patientDetail.gender,
            ageRange = patientDetail.ageRange
        )

        fireBaseDatabase.reference.child(SCREEN_LOG_CHILD).push().setValue(screenLog)
    }

    companion object {
        private const val SCREEN_LOG_CHILD = "screenLog"
    }
}
