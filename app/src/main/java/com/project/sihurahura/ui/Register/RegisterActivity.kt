package com.project.sihurahura.ui.Register

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.project.sihurahura.data.model.signup.Signup
import com.project.sihurahura.databinding.ActivityRegisterBinding
import com.project.sihurahura.ui.Main.MainActivity
import com.project.sihurahura.util.VmData
import com.project.sihurahura.util.toast

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by viewBinding()
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        with(binding) {

            signupButton.setOnClickListener {
                val usr = username.text.toString().trim()
                val email = email.text.toString().trim()
                val passw = password.text.toString().trim()

                if (usr.isEmpty() && email.isEmpty() && passw.isEmpty()) {
                    toast("Lengkapi Data Dulu . . .")
                } else {
                    viewModel.postSignUp(
                        Signup(
                            email,
                            passw,
                            usr
                        )
                    )
                }
            }
        }
        setObservableSignUp()
    }

    private fun setObservableSignUp() {
        viewModel.postSignUp.observe(this) {
            when (it) {
                is VmData.Loading -> {
                    toast("Loading . . .")
                }

                is VmData.Success -> {
                    if (it.data.statusCode == 409) {
                        toast("${it.data.message}")
                    } else {
                        toast("Berhasil Mendaftar . . .")
                        intent = Intent(this, MainActivity::class.java)
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