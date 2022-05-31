package com.project.sihurahura.data.repository

import com.project.sihurahura.data.model.addData.AddData
import com.project.sihurahura.data.model.addData.AddDataResponse
import com.project.sihurahura.data.model.login.Login
import com.project.sihurahura.data.model.login.LoginResponse
import com.project.sihurahura.data.model.signup.SignUpResponse
import com.project.sihurahura.data.model.signup.Signup
import com.project.sihurahura.data.network.Api
import io.reactivex.Single

class UserRepository {

    fun signUp(
        data: Signup
    ): Single<SignUpResponse> {
        return Api.getApi().signUp(data).map {
            it
        }
    }

    fun login(
        data: Login
    ): Single<LoginResponse> {
        return Api.getApi().login(data).map {
            it
        }
    }

    fun addDataUser(
        data: AddData
    ): Single<AddDataResponse> {
        return Api.getApi().addDataUser(data).map {
            it
        }
    }
}