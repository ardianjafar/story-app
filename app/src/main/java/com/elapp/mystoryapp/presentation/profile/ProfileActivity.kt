package com.elapp.mystoryapp.presentation.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.elapp.mystoryapp.R.string
import com.elapp.mystoryapp.databinding.ActivityProfileBinding
import com.elapp.mystoryapp.presentation.login.LoginActivity
import com.elapp.mystoryapp.utils.SessionManager

class ProfileActivity : AppCompatActivity() {

    private var _activityProfileBinding: ActivityProfileBinding? = null
    private val binding get() = _activityProfileBinding!!

    private lateinit var pref: SessionManager

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityProfileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(_activityProfileBinding?.root)

        pref = SessionManager(this)
        initUI()
        initAction()
    }

    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(string.title_profile)

        binding.tvUserName.text = pref.getUserName
        binding.tvUserEmail.text = pref.getEmail
    }

    private fun initAction() {
        binding.btnLogout.setOnClickListener {
            openLogoutDialog()
        }
    }

    private fun openLogoutDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(string.message_logout_confirm))
            ?.setPositiveButton(getString(string.action_yes)) { _, _ ->
                pref.clearPreferences()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
            ?.setNegativeButton(getString(string.action_cancel), null)
        val alert = alertDialog.create()
        alert.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}