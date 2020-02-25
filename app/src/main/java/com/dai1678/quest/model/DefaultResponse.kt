package com.dai1678.quest.model

/**
 * APIから特定のデータを含まない場合に返されるデフォルトレスポンス
 * @param statusCode ステータスコード
 * @param isSuccess 処理が成功したかどうか
 * @param message メッセージ
 */
data class DefaultResponse(
    val statusCode: Int,
    val isSuccess: Boolean,
    val message: String
)
