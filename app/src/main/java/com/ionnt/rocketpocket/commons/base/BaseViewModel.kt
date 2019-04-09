package com.ionnt.rocketpocket.commons.base

import androidx.lifecycle.ViewModel
import com.ionnt.rocketpocket.commons.utils.Failure
import com.ionnt.rocketpocket.commons.utils.SingleLivedata

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

abstract class BaseViewModel: ViewModel() {
    var failure = SingleLivedata<Failure>()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    override fun onCleared() {
        super.onCleared()
        cancelRequest()
    }

    abstract fun cancelRequest()
}