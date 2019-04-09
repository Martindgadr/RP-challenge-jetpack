package com.ionnt.rocketpocket.commons.utils

import android.content.Context
import com.ionnt.rocketpocket.commons.extensions.networkState
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkState?.isConnected
}