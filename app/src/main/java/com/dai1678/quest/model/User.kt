package com.dai1678.quest.model

import java.util.UUID

/**
 * 受検者のデータクラス
 *
 * @param id UUIDで定義されるランダムな文字列
 * @param firstName 受検者の名
 * @param lastName 受検者の姓
 * @param firstNameReading 受検者の名の読み仮名
 * @param lastNameReading 受検者の姓の読み仮名
 * @param gender 受検者の性別
 * @param ageRange 受検者の年齢範囲
 * @param questionnaires 受検者のアンケート回答結果
 */
data class User(
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val firstNameReading: String,
    val lastNameReading: String,
    val gender: String,
    val ageRange: String,
    val questionnaires: List<Questionnaire> = listOf()
)

/**
 * APIで受検者情報を受け取った際のレスポンスのデータクラス
 *
 * @param total 受け取った受検者情報の数
 * @param list 受け取った受検者情報
 */
data class PatientListResponse(
    val total: Int,
    val list: List<User> = listOf()
)
