package com.dai1678.quest.ui.createUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentCreateUserBinding

class CreateUserFragment : Fragment() {

    private val createUserViewModel: CreateUserViewModel by viewModels()
    private lateinit var binding: FragmentCreateUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateUserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CreateUserFragment
            viewModel = createUserViewModel
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
