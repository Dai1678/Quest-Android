package com.dai1678.quest.ui.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.LoginFragmentBinding
import com.dai1678.quest.ui.login.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.dai1678.quest.ui.login.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener {
            binding.loginProgressBar.visibility = View.VISIBLE
            binding.loginButton.isEnabled = false
            viewModel.onClickLogin()
        }

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            binding.loginProgressBar.visibility = View.INVISIBLE
            binding.loginButton.isEnabled = true

            if (it == AUTHENTICATED) {
                startNavigation(view)
            } else if (it == INVALID_AUTHENTICATION) {
                showLoginError(view)
            }
        })
    }

    // 画面遷移
    private fun startNavigation(view: View) {
        Snackbar.make(
            view,
            R.string.login_success_message,
            Snackbar.LENGTH_LONG
        ).apply {
            this.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            val snackBarText = this.view.findViewById<TextView>(
                com.google.android.material.R.id.snackbar_text
            )
            snackBarText.setTextColor(Color.WHITE)
            this.show()
        }

        findNavController().navigate(R.id.action_loginFragment_to_patientListFragment)
    }

    private fun showLoginError(view: View) {
        Snackbar.make(
            view,
            R.string.login_failed_message,
            Snackbar.LENGTH_LONG
        ).apply {
            this.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            val snackBarText = this.view.findViewById<TextView>(
                com.google.android.material.R.id.snackbar_text
            )
            snackBarText.setTextColor(Color.WHITE)
            this.show()
        }
    }
}
