package com.manyan.mystoryapp.presentation.profile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manyan.mystoryapp.R
import com.manyan.mystoryapp.databinding.ActivityAboutDevBinding
import com.manyan.mystoryapp.databinding.ActivityProfileBinding

class AboutDev : AppCompatActivity() {

    private var _activityAboutDev: ActivityAboutDevBinding? = null
    private val binding get() = _activityAboutDev!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityAboutDev = ActivityAboutDevBinding.inflate(layoutInflater)
        setContentView(_activityAboutDev?.root)

        openProfile()
    }

    private fun openProfile() {
        binding.btnCall.setOnClickListener {
            val uri = Uri.parse("https://github.com/ardianjafar")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}