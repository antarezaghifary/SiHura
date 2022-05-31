package com.project.sihurahura.data.network

import com.project.sihurahura.data.model.addData.AddData
import com.project.sihurahura.data.model.addData.AddDataResponse
import com.project.sihurahura.data.model.login.Login
import com.project.sihurahura.data.model.login.LoginResponse
import com.project.sihurahura.data.model.signup.SignUpResponse
import com.project.sihurahura.data.model.signup.Signup
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {
    @POST("signup")
    fun signUp(
        @Body raw: Signup
    ): Single<SignUpResponse>

    @POST("login")
    fun login(
        @Body raw: Login
    ): Single<LoginResponse>

    @POST("registrasi")
    fun addDataUser(
        @Body raw: AddData
    ): Single<AddDataResponse>
}