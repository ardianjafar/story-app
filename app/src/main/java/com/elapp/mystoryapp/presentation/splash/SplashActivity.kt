package com.elapp.mystoryapp.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.elapp.mystoryapp.MainActivity
import com.elapp.mystoryapp.R
import com.elapp.mystoryapp.presentation.login.LoginActivity
import com.elapp.mystoryapp.utils.SessionManager
import com.elapp.mystoryapp.utils.UiConstValue

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var pref: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        pref = SessionManager(this)
        val isLogin = pref.isLogin
        Handler(Looper.getMainLooper()).postDelayed({
            when {
                isLogin -> {
                    MainActivity.start(this)
                    finish()
                }
                else -> {
                    LoginActivity.start(this)
                    finish()
                }
            }
        }, UiConstValue.LOADING_TIME)
    }
}