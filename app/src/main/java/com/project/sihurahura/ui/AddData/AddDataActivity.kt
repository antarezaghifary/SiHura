package com.project.sihurahura.ui.AddData

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.project.sihurahura.R
import com.project.sihurahura.databinding.ActivityAddDataBinding

class AddDataActivity : AppCompatActivity() {
    private val binding: ActivityAddDataBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
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
                    collapsingToolbar.title = "Beasiswa"
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

        }
    }
}