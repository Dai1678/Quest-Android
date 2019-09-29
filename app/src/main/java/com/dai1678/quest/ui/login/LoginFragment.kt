package com.dai1678.quest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentLoginBinding
import com.dai1678.quest.entity.Doctor2
import com.google.android.material.appbar.AppBarLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.ViewHolder
import kotlin.math.abs

class LoginFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
//        binding.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // offset 0.0 is Fully expanded to 1.0
        binding.loginAppBar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
                binding.expandAppBarProgress = abs(offset / appBarLayout.totalScrollRange.toFloat())
            }
        )

        // TODO viewModel.getDoctorList
        val doctorLogin = Doctor2("test", "テスト", "ユーザー", "2019-09-23T19:27:00.000Z", false)

        val section = Section()
        for (i in 0..10) section.add(DoctorGroup(doctorLogin))
        groupAdapter.add(section)

        binding.loginDoctorList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        // TODO viewModel.getDoctorList
    }
}
