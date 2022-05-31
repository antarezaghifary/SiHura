package com.project.sihurahura.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)
