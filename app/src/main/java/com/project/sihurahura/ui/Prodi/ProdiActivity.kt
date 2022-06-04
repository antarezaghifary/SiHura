package com.project.sihurahura.ui.Prodi

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.project.sihurahura.R
import com.project.sihurahura.databinding.ActivityProdiBinding

class ProdiActivity : AppCompatActivity() {

    private val binding: ActivityProdiBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            setSupportActionBar(toolbarInformasi)

            var isShow = true
            var scrollRange = -1
            binding.appBarInformasi.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = barLayout?.totalScrollRange!!
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = "Program Studi"
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    toolbarInformasi.setNavigationIcon(R.drawable.ic_arrow_24dp)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    isShow = false
                }
            })
            btnBackIsolasi.setOnClickListener {
                finish()
            }
        }

    }
}