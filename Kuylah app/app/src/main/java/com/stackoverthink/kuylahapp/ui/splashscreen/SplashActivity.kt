package com.stackoverthink.kuylahapp.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stackoverthink.kuylahapp.R
import com.stackoverthink.kuylahapp.ui.auth.AuthenticationActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            startActivity(Intent(this@SplashActivity, AuthenticationActivity::class.java))
            finish()
        }
    }
}