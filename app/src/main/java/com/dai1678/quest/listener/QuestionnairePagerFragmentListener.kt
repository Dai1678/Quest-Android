package com.dai1678.quest.listener

import android.view.View

interface QuestionnairePagerFragmentListener {
    /**
     * 前に戻るボタンを押した時
     * @param view View
     */
    fun onClickBack(view: View)

    /**
     * 次に進むボタンを押した時
     * @param view View
     */
    fun onClickNext(view: View)
}
