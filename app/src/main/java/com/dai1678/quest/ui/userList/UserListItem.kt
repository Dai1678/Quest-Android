package com.dai1678.quest.ui.userList

import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListHeaderUserBinding
import com.dai1678.quest.databinding.ListItemUserBinding
import com.dai1678.quest.enums.DateFormat
import com.dai1678.quest.model.User
import com.xwray.groupie.databinding.BindableItem
import java.util.Date

/**
 * 受検者リスト画面 並び替えヘッダー groupie Item // TODO 未開発
 *
 * @param sortCategoryName 現在のソート種類名
 */
class UserListHeaderItem(private val sortCategoryName: String) :
    BindableItem<ListHeaderUserBinding>() {

    override fun getLayout() = R.layout.list_header_user

    override fun bind(viewBinding: ListHeaderUserBinding, position: Int) {
        viewBinding.sortCategoryName = sortCategoryName

        viewBinding.listHeaderSort.setOnClickListener {
            // TODO bottomLayoutを表示して並び替えメニュー起動 -> 反映
        }
    }
}

/**
 * 受検者リスト画面 受検者情報 groupie Item
 *
 * @param user 受検者情報
 * @param callback 受検者リストクリック時のコールバック
 */
class UserListBodyItem(val user: User, val callback: () -> Unit) :
    BindableItem<ListItemUserBinding>() {

    override fun getLayout() = R.layout.list_item_user

    override fun bind(viewBinding: ListItemUserBinding, position: Int) {
        val resources = viewBinding.root.resources
        viewBinding.userName = "${user.lastName} ${user.firstName}"
        viewBinding.userNameReading = "${user.lastNameReading} ${user.firstNameReading}"

        val lastQuestionnaireDate = if (user.questionnaires.isEmpty()) {
            resources.getString(R.string.user_list_none_last_questionnaire_label)
        } else {
            val date =
                DateFormat.YYYYMMDD_TIMEZONE_HHMMSS.parse(user.questionnaires[0].updatedAt)
            DateFormat.YYYYMD_JP.format(date ?: Date())
        }

        viewBinding.lastQuestionnaireTime =
            resources.getString(R.string.user_list_last_questionnaire_label, lastQuestionnaireDate)

        viewBinding.listItemUserView.setOnClickListener {
            callback.invoke()
        }
    }
}
