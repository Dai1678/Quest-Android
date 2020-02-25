package com.dai1678.quest.listener

import android.widget.RadioGroup

interface QuestionnaireAnswerFragmentListener {
    /**
     * ラジオボタンが押されたとき
     * @param radioGroup RadioGroup
     * @param id 押されたラジオボタンのid
     */
    fun onChangeAnswer(radioGroup: RadioGroup, id: Int)
}
