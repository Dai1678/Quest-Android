package com.dai1678.quest.listener

import android.widget.RadioGroup

interface QuestionnaireAnswerFragmentListener {

    /**
     * ラジオボタンが押されたとき
     */
    fun onChangeAnswer(radioGroup: RadioGroup, id: Int)
}
