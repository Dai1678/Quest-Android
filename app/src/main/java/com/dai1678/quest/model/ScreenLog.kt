package com.dai1678.quest.model

/**
 * Firebase Realtime Databaseに保存されるスクリーンログのデータクラス
 *
 * @param patientId 受検者のid
 * @param currentPage: 現在表示されている回答画面のページ番号
 * @param currentTime 現在時刻 //TODO Date型もしくは日付フォーマットされたString型にする
 * @param scrollCount 回答画面内で設問表示を切り替えた回数
 * @param gender 受検者の性別
 * @param ageRange 受検者の年齢範囲
 */
data class ScreenLog(
    val patientId: String,
    val currentPage: Int,
    val currentTime: Long,
    val scrollCount: Int,
    val gender: String,
    val ageRange: String
)
