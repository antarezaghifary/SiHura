package com.project.sihurahura.ui.AddData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.project.sihurahura.data.model.addData.AddData
import com.project.sihurahura.data.model.addData.AddDataResponse
import com.project.sihurahura.data.repository.UserRepository
import com.project.sihurahura.util.VmData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException

class AddDataViewModel : ViewModel() {
    val addDataUser: MutableLiveData<VmData<AddDataResponse>> by liveData(VmData.default())

    private val repository: UserRepository by lazy {
        UserRepository()
    }

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun postDataUSer(user: AddData) {
        addDataUser.value = VmData.loading()
        repository.addDataUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                addDataUser.value = VmData.success(it)
            }, {
                if (it is HttpException) {
                    it.response()?.errorBody()?.string()?.let { response ->
                        val message = JSONObject(response).getString("message")
                        addDataUser.value = VmData.fail(it, message)
                    }
                } else {
                    addDataUser.value = VmData.fail(it, it.message)
                }
            }).let { return@let compositeDisposable::add }
    }
}