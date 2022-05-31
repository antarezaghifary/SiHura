package com.project.sihurahura.ui.Register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.project.sihurahura.data.model.signup.SignUpResponse
import com.project.sihurahura.data.model.signup.Signup
import com.project.sihurahura.data.repository.UserRepository
import com.project.sihurahura.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class SignUpViewModel : ViewModel() {
    val postSignUp: MutableLiveData<VmData<SignUpResponse>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun postSignUp(user: Signup) {
        postSignUp.value = VmData.loading()
        repository.signUp(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postSignUp.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        postSignUp.value = VmData.fail(it, message)
                    }
                } else {
                    postSignUp.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}