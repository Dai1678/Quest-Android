package com.dai1678.quest.ui.userList

import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListHeaderUserBinding
import com.dai1678.quest.databinding.ListItemUserBinding
import com.dai1678.quest.model.Patient
import com.dai1678.quest.util.StringUtils
import com.xwray.groupie.databinding.BindableItem

/**
 * 受検者リスト画面 並び替えヘッダー
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
 * 受検者リスト画面 受検者情報
 */
class UserListBodyItem(val patient: Patient, val callback: () -> Unit) :
    BindableItem<ListItemUserBinding>(), StringUtils {

    override fun getLayout() = R.layout.list_item_user

    override fun bind(viewBinding: ListItemUserBinding, position: Int) {
        viewBinding.patientName = "${patient.lastName} ${patient.firstName}"
        viewBinding.patientNameReading = "${patient.lastNameReading} ${patient.firstNameReading}"

        viewBinding.lastQuestionnaireTime =
            viewBinding.root.resources.getString(R.string.user_list_last_questionnaire_label) +
                    if (patient.questionnaires.isEmpty()) {
                        viewBinding.root.resources.getString(
                            R.string.user_list_none_last_questionnaire_label
                        )
                    } else {
                        patient.questionnaires[0].updatedAt.formatDateStr
                    }

        // 受検者確認ダイアログの表示
        viewBinding.listItemPatientView.setOnClickListener {
            callback.invoke()
        }
    }
}
