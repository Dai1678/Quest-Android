package com.dai1678.quest.listener

import android.view.View

interface QuestionnaireEndFragmentListener {
    /**
     * 回答送信ボタンを押した時
     * @param view View
     */
    fun onClickSendAnswer(view: View)
}
