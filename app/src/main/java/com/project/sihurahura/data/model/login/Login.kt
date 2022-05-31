package com.project.sihurahura.data.model.login

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("username") val username: String?,
    @SerializedName("passw") val passw: String?,
)
