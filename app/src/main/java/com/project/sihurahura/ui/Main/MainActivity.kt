package com.project.sihurahura.ui.Main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.toast
import com.project.sihurahura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            loginButton.setOnClickListener {

                val mEmail: String = email.text.toString()
                val mPass: String = password.text.toString()
                val init = mEmail.isNotEmpty() && mPass.isNotEmpty()

                if (init) {
                    toast("oke!")
                } else {
                    toast("input dengan benar")
                }
            }

            registerButton.setOnClickListener {
                toast("daftar")
            }
        }
    }
}