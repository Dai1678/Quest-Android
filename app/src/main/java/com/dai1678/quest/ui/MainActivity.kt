package com.dai1678.quest.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_main_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.userListFragment, R.id.questionnairePagerFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
