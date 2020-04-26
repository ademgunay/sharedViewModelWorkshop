package com.gunaya.sharedviewmodeldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val data: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(String::class.java).newInstance(data)
    }
}

class MainViewModel(val data: String) : ViewModel() {

    val showInterfaceToast = MutableLiveData<Unit>()
    val showSharedVMToast = MutableLiveData<Unit>()
    val userEntity = MutableLiveData<DemoUserEntity>(
        DemoUserEntity(
            name = "Data from MainActivity",
            age = "Data from MainActivity",
            job = "Data from MainActivity"
        )
    )
    // TODO Use this for LiveData for the workshop
    val workshopText = MutableLiveData<String>()

    fun onShowInterfaceToastClick() {
        showInterfaceToast.value = Unit
    }

    fun onShowVMToastClick() {
        showSharedVMToast.value = Unit
    }

    fun showUserUsingInterface() {
        userEntity.value = DemoUserEntity(name = "Interface", age = "555", job = "Retired")
    }

    fun showUserUsingSharedVM() {
        userEntity.value = DemoUserEntity(name = "ViewModel", age = "69", job = "Cool guy")
    }

}