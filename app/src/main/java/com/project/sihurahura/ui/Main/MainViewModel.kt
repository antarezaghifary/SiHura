package com.project.sihurahura.ui.Main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.project.sihurahura.data.model.login.Login
import com.project.sihurahura.data.model.login.LoginResponse
import com.project.sihurahura.data.repository.UserRepository
import com.project.sihurahura.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    val postLogin: MutableLiveData<VmData<LoginResponse>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun postLogin(user: Login) {
        postLogin.value = VmData.loading()
        repository.login(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postLogin.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        postLogin.value = VmData.fail(it, message)
                    }
                } else {
                    postLogin.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}