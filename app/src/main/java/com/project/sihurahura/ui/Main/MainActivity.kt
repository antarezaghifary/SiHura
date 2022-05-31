package com.project.sihurahura.ui.Main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.project.sihurahura.data.model.login.Login
import com.project.sihurahura.databinding.ActivityMainBinding
import com.project.sihurahura.ui.Home.HomeActivity
import com.project.sihurahura.ui.Register.RegisterActivity
import com.project.sihurahura.util.VmData

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    companion object {
        var BACK_PRESSED: Long = 0L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        with(binding) {
            loginButton.setOnClickListener {

                val mUser: String = username.text.toString()
                val mPass: String = password.text.toString()
                val init = mUser.isNotEmpty() && mPass.isNotEmpty()

                if (init) {
                    viewModel.postLogin(
                        Login(
                            mUser,
                            mPass
                        )
                    )
                } else {
                    toast("input dengan benar")
                }
            }

            registerButton.setOnClickListener {
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
        setObservableLogin()
    }

    override fun onBackPressed() {
        if ((BACK_PRESSED + 2000L) > System.currentTimeMillis()) {
            finish()
        } else {
            Toast.makeText(this, "Tekan Sekali Lagi", Toast.LENGTH_SHORT).show()
        }
        BACK_PRESSED = System.currentTimeMillis()
    }

    private fun setObservableLogin() {
        viewModel.postLogin.observe(this) {
            when (it) {

                is VmData.Loading -> {
                    toast("Loading . . .")
                }

                is VmData.Success -> {
                    if (it.data.statusCode == 401) {
                        toast("${it.data.message}")
                    } else {
                        toast("Berhasil Login . . .")
                        intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                is VmData.Failure -> {
                    toast("${it.message}")
                }
            }
        }
    }

}