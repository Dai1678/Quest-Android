package com.dai1678.quest.ui.login

//import android.graphics.Color
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.core.content.ContextCompat
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.observe
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.dai1678.quest.R
//import com.dai1678.quest.databinding.FragmentLoginBinding
//import com.google.android.material.appbar.AppBarLayout
//import com.google.android.material.snackbar.Snackbar
//import com.xwray.groupie.Group
//import com.xwray.groupie.GroupAdapter
//import com.xwray.groupie.databinding.ViewHolder
//import kotlin.math.abs
//
//class LoginFragment : Fragment() {
//
//    private val viewModel: LoginViewModel by viewModels()
//    private val groupAdapter = GroupAdapter<ViewHolder<*>>()
//    private lateinit var binding: FragmentLoginBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_login, container, false
//        )
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // offset 0.0 is Fully expanded to 1.0
//        binding.loginAppBar.addOnOffsetChangedListener(
//            AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
//                binding.expandAppBarProgress = abs(offset / appBarLayout.totalScrollRange.toFloat())
//            }
//        )
//
//        binding.loginToolbar.apply {
//            inflateMenu(R.menu.login_menu)
//            setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.reload_doctor_list -> {
//                        viewModel.reloadDoctorList()
//                        makeDoctorList()
//                    }
//                }
//                true
//            }
//        }
//
//        viewModel.isLoading().observe(viewLifecycleOwner) {
//            binding.loginProgressBar.visibility = if (it) View.VISIBLE else View.GONE
//        }
//
//        viewModel.getSnackBarAction().observe(viewLifecycleOwner) {
//            Snackbar.make(view, it.text, Snackbar.LENGTH_LONG).apply {
//                getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
//                getView().findViewById<TextView>(
//                    com.google.android.material.R.id.snackbar_text
//                ).apply {
//                    setTextColor(Color.WHITE)
//                }
//            }.show()
//        }
//
//        binding.loginDoctorList.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = groupAdapter
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        makeDoctorList()
//    }
//
//    private fun makeDoctorList() {
//        val groupList = arrayListOf<Group>()
//
//        viewModel.getDoctorList().observe(viewLifecycleOwner) {
//
//            it?.let {
//                for (doctor in it.list) {
//                    groupList.add(DoctorGroup(doctor))
//                }
//                groupAdapter.update(groupList)
//            }
//        }
//    }
//}
