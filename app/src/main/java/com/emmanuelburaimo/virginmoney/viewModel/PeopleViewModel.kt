package com.emmanuelburaimo.virginmoney.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emmanuelburaimo.virginmoney.repository.model.PersonaModel
import com.emmanuelburaimo.virginmoney.repository.service.AppService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PeopleViewModel : ViewModel() {

    private val appService = AppService()
    private val disposable = CompositeDisposable()

    val people = MutableLiveData<List<PersonaModel>>()
    val peopleError = MutableLiveData<Boolean>()
    val peopleLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromApi()
    }

    private fun getDataFromApi() {
        peopleLoading.value = true

        disposable.add(
            appService.getPeople()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PersonaModel>>() {

                    override fun onSuccess(peopleData: List<PersonaModel>) {
                        people.value = peopleData
                        peopleError.value = false
                        peopleLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        peopleLoading.value = false
                        peopleError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

}