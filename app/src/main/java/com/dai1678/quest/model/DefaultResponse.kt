package com.dai1678.quest.model

/**
 * APIから特定のデータを含まない場合に返されるデフォルトレスポンス
 */
data class DefaultResponse(
    val statusCode: Int,
    val isSuccess: Boolean,
    val message: String
)
