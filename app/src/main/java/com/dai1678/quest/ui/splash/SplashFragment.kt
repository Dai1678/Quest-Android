package com.dai1678.quest.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.dai1678.quest.R
import com.dai1678.quest.ui.login.LoginViewModel.*
import com.dai1678.quest.ui.login.LoginViewModel.AuthenticationState.*
import com.dai1678.quest.util.PreferenceService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch {
            delay(2000)

            val token = PreferenceService.getAuthToken()

            val navController = findNavController()
            if (token != null) {
                navController.navigate(R.id.action_splashFragment_to_patientListFragment)
            } else {
                navController.navigate(R.id.action_splashFragment_to_login_navigation)
            }
        }
    }
}
