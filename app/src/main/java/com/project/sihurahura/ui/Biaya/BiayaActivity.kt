package com.project.sihurahura.ui.Biaya

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.project.sihurahura.R
import com.project.sihurahura.databinding.ActivityBiayaBinding

class BiayaActivity : AppCompatActivity() {

    private val binding: ActivityBiayaBinding by viewBinding()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    collapsingToolbar.title = "Biaya Perkuliahan"
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
            }

            biaya1.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            biaya2.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            biaya3.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            biaya4.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            biaya5.justificationMode = JUSTIFICATION_MODE_INTER_WORD

        }
    }
}