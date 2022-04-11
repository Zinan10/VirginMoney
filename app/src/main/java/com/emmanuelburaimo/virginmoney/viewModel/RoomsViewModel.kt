package com.emmanuelburaimo.virginmoney.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emmanuelburaimo.virginmoney.repository.model.PersonaModel
import com.emmanuelburaimo.virginmoney.repository.model.RoomModel
import com.emmanuelburaimo.virginmoney.repository.service.AppService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RoomsViewModel : ViewModel() {

    private val appService = AppService()
    private val disposable = CompositeDisposable()

    val rooms = MutableLiveData<List<RoomModel>>()
    val roomsError = MutableLiveData<Boolean>()
    val roomsLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromApi()
    }

    private fun getDataFromApi() {
        roomsLoading.value = true

        disposable.add(
            appService.getRooms()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<RoomModel>>() {

                    override fun onSuccess(roomsData: List<RoomModel>) {
                        rooms.value = roomsData
                        roomsError.value = false
                        roomsLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        roomsLoading.value = false
                        roomsError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }


}