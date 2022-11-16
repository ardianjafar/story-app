package com.manyan.mystoryapp.ui

import android.R
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.manyan.mystoryapp.databinding.ActivityAboutDevBinding

class AboutDev : AppCompatActivity() {

    private var _activityAboutDev: ActivityAboutDevBinding? = null
    private val binding get() = _activityAboutDev!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityAboutDev = ActivityAboutDevBinding.inflate(layoutInflater)
        setContentView(_activityAboutDev?.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(com.manyan.mystoryapp.R.string.story_location)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Profile"
        openMyProfile()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openMyProfile() {
        binding.btnCall.setOnClickListener {
            val uri = Uri.parse("https://github.com/ardianjafar")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

}