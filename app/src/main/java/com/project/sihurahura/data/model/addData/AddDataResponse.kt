package com.project.sihurahura.data.model.addData

import com.google.gson.annotations.SerializedName

data class AddDataResponse(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)
