package com.project.sihurahura.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

@SuppressLint("CheckResult")
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}