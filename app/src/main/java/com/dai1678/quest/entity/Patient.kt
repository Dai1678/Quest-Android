package com.dai1678.quest.entity

import org.json.JSONArray

data class Patient(
    private var patientId: Long,
    private var hospitalId: Long,
    private var patientName: String,
    private var questionnaireData: JSONArray
)
