package com.project.sihurahura.ui.Main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.project.sihurahura.databinding.ActivityMainBinding
import com.project.sihurahura.ui.Home.HomeActivity
import com.project.sihurahura.ui.Register.RegisterActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

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

                val mEmail: String = email.text.toString()
                val mPass: String = password.text.toString()
                val init = mEmail.isNotEmpty() && mPass.isNotEmpty()

                if (init) {
                    toast("oke!")
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    toast("input dengan benar")
                }
            }

            registerButton.setOnClickListener {
                toast("daftar")
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        if ((BACK_PRESSED + 2000L) > System.currentTimeMillis()) {
            finish()
        } else {
            Toast.makeText(this, "Tekan Sekali Lagi", Toast.LENGTH_SHORT).show()
        }
        BACK_PRESSED = System.currentTimeMillis()
    }
}