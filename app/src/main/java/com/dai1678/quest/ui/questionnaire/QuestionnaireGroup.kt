package com.dai1678.quest.ui.questionnaire

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListItemQuestionnaireBinding
import com.dai1678.quest.entity.QuestionChild
import com.xwray.groupie.databinding.BindableItem

class QuestionnaireGroup(
    private val viewModel: QuestionnaireViewModel,
    private val questionChild: QuestionChild,
    private val isQuestions: Boolean
) : BindableItem<ListItemQuestionnaireBinding>() {

    private val currentPageNumber = viewModel.getPage().value ?: 0

    override fun getLayout(): Int = R.layout.list_item_questionnaire

    override fun bind(viewBinding: ListItemQuestionnaireBinding, position: Int) {

        if (isQuestions) {
            viewBinding.isShowQuestionMessage = true
            viewBinding.questionnaireSubNumberText.text = questionChild.questionChildNumberMessage
            viewBinding.questionnaireSubMessageText.text = questionChild.questionChildMessage
        } else {
            viewBinding.isShowQuestionMessage = false
        }

        viewBinding.questionnaireRadioGroup.removeAllViews()

        val radioButtonArray = arrayOfNulls<RadioButton>(questionChild.selectAnswerMessage.size)
        for (i in radioButtonArray.indices) {
            radioButtonArray[i] = RadioButton(viewBinding.root.context).apply {

                // 現在のページまでの問題数 + position = 現在のページの問題番号 (0からスタート)
                val questionSizeUntilCurrentPage =
                    viewModel.getQuestionSizeUntilCurrentPage(currentPageNumber) + position

                id = View.generateViewId()
                tag = questionSizeUntilCurrentPage
                text = questionChild.selectAnswerMessage[i]
                textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    8F,
                    context.resources.displayMetrics
                )
                buttonTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimary))

                val buttonLayoutParams: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                val bottomMargin =
                    viewBinding.root.context.resources.getDimension(
                        R.dimen.radio_button_vertical_margin
                    ).toInt()
                buttonLayoutParams.setMargins(0, 0, 0, bottomMargin)

                layoutParams = buttonLayoutParams

                val setUpCheckPosition =
                    viewModel.selectRadioButtonPositions[questionSizeUntilCurrentPage] ?: 0
                if (i == setUpCheckPosition) {
                    toggle()
                    viewModel.selectRadioButtonTexts[questionSizeUntilCurrentPage] =
                        text.toString()
                }

                viewBinding.questionnaireRadioGroup.addView(this)
            }
        }

        viewBinding.questionnaireRadioGroup.setOnCheckedChangeListener { radioGroup, _ ->
            val selectedRadioButton =
                viewBinding.root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            val currentQuestionNumber = selectedRadioButton.tag as Int

            viewModel.selectRadioButtonPositions[currentQuestionNumber] =
                radioGroup.indexOfChild(selectedRadioButton)

            viewModel.selectRadioButtonTexts[currentQuestionNumber] =
                selectedRadioButton.text.toString()
        }
    }
}
