package com.dai1678.quest.model

import java.util.UUID

/**
 * ユーザーのデータクラス
 *
 * @param id UUIDで定義されるランダムな文字列
 * @param firstName ユーザーの名
 * @param lastName ユーザーの姓
 * @param firstNameReading ユーザーの名の読み仮名
 * @param lastNameReading ユーザーの姓の読み仮名
 * @param gender ユーザーの性別
 * @param ageRange ユーザーの年齢範囲
 * @param questionnaires ユーザーのアンケート回答結果
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
 * APIでユーザー情報を受け取った際のレスポンスのデータクラス
 *
 * @param total 受け取ったユーザーデータの数
 * @param list 受け取ったユーザーデータ
 */
data class PatientListResponse(
    val total: Int,
    val list: List<User> = listOf()
)
