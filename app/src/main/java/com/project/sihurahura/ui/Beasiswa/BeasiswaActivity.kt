package com.project.sihurahura.ui.Beasiswa

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.project.sihurahura.R
import com.project.sihurahura.databinding.ActivityBeasiswaBinding

class BeasiswaActivity : AppCompatActivity() {
    private val binding: ActivityBeasiswaBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            with(binding) {
                setSupportActionBar(toolbarIsolasi)

                var isShow = true
                var scrollRange = -1
                binding.appBarIsolasi.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                    if (scrollRange == -1) {
                        scrollRange = barLayout?.totalScrollRange!!
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbar.title = "Panduan Isolasi Mandiri"
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        toolbarIsolasi.setNavigationIcon(R.drawable.ic_arrow_24dp)
                        isShow = true
                    } else if (isShow) {
                        collapsingToolbar.title = " "
                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                        isShow = false
                    }
                })
                btnBackIsolasi.setOnClickListener {
                    finish()
                    super.onBackPressed()
                }
            }
        }
    }
}