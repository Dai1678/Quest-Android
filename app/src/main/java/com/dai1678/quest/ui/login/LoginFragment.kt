package com.dai1678.quest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dai1678.quest.R
import com.dai1678.quest.databinding.LoginFragmentBinding
import com.dai1678.quest.ui.patientList.PatientListActivity

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this.requireActivity()).get(LoginViewModel::class.java)
    }

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        ) as LoginFragmentBinding
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.loginButton.setOnClickListener {
            binding.loginProgressBar.visibility = View.VISIBLE
            binding.loginButton.isEnabled = false
            viewModel.onClickLogin()
        }

        viewModel.loginStatus.observe(this, Observer {
            binding.loginProgressBar.visibility = View.INVISIBLE
            binding.loginButton.isEnabled = true
            startNavigation(it)
        })
    }

    // 画面遷移
    private fun startNavigation(result: Boolean) {
        if (result) {
            Toast.makeText(
                context, resources.getString(R.string.login_success_message), Toast.LENGTH_SHORT
            ).show()
            val intent = PatientListActivity.createIntent(requireActivity())
            startActivity(intent)
            requireActivity().finish()
        } else {
            // TODO ログイン失敗
            Toast.makeText(
                context, resources.getString(R.string.login_failed_message), Toast.LENGTH_SHORT
            ).show()
        }
    }
}
