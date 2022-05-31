package com.project.sihurahura.data.network

import com.oratakashi.viewbinding.core.tools.retrofit.createOkHttpClient
import com.oratakashi.viewbinding.core.tools.retrofit.createReactiveService

object Api {
    fun getApi(): MyApi {
        return createReactiveService(
            MyApi::class.java,
            createOkHttpClient(
                interceptors = arrayOf(),
                authenticator = null,
                showDebugLog = true,
                pinner = null
            ),
            "https://epmb-app.herokuapp.com/"
        )
    }
}