package com.dai1678.quest.ui.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dai1678.quest.R

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }

    companion object {
        fun createIntent(context: Context) = Intent(
            context,
            CreateUserActivity::class.java
        )
    }
}
