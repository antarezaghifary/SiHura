package com.project.sihurahura.data.model.signup

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)
