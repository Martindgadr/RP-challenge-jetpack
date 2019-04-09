package com.ionnt.rocketpocket.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */
@Parcelize
data class Store(
    val address: String,
    val city: String,
    val name: String,
    val latitude: String,
    val zipcode: String,
    @SerializedName("storeLogoURL")
    val logo: String,
    val phone: String,
    val longitude: String,
    val storeID: String,
    val state: String
) : Parcelable
