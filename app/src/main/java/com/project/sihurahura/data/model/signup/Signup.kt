package com.project.sihurahura.data.model.signup

import com.google.gson.annotations.SerializedName

data class Signup(
    @SerializedName("email") val email: String?,
    @SerializedName("passw") val passw: String?,
    @SerializedName("username") val username: String?,
)
