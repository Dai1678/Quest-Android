package com.dai1678.quest.ui.registerUser

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentRegisterUserBinding
import com.dai1678.quest.listener.RegisterUserFragmentListener
import com.google.android.material.snackbar.Snackbar

class RegisterUserFragment : Fragment() {

    private val viewModel: RegisterUserViewModel by viewModels()
    private lateinit var binding: FragmentRegisterUserBinding

    private val registerUserFragmentListener = object : RegisterUserFragmentListener {
        override fun onClickRegisterUserData(view: View) {
            viewModel.registerUserData()
        }
    }

    private val callbackListener = object : RegisterUserViewModel.Callback {
        override fun finishQuestionnaire() {
            val context = context ?: return
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            findNavController().navigate(R.id.action_global_user_list_fragment)
        }

        override fun showErrorSnackBar(message: String) {
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).apply {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                view.findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_text
                ).apply {
                    setTextColor(Color.WHITE)
                }
            }.show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        view?.setupSnackBar(this, viewModel.snackBarText, Toast.LENGTH_SHORT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterUserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@RegisterUserFragment
            viewModel = this@RegisterUserFragment.viewModel.apply {
                callback = this@RegisterUserFragment.callbackListener
            }
            listener = this@RegisterUserFragment.registerUserFragmentListener
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAgeExposedDropdown()
        setupGenderExposedDropdown()
    }

    private fun setupAgeExposedDropdown() {
        val context = context ?: return
        val ages = resources.getStringArray(R.array.age_range_array)
        val adapter = ArrayAdapter<String>(
            context,
            R.layout.dropdown_menu_popup_item,
            ages
        )
        binding.createUserInputInfo.inputDropdownAge.apply {
            setAdapter(adapter)
            keyListener = null // 入力できないようにする
        }
    }

    private fun setupGenderExposedDropdown() {
        val context = context ?: return
        val genders = resources.getStringArray(R.array.gender_array)
        val adapter = ArrayAdapter<String>(
            context,
            R.layout.dropdown_menu_popup_item,
            genders
        )
        binding.createUserInputInfo.inputDropdownGender.apply {
            setAdapter(adapter)
            keyListener = null // 入力できないようにする
        }
    }
}
