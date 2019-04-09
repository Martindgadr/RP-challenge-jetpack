package com.ionnt.rocketpocket.data.dtos

import com.ionnt.rocketpocket.data.model.Store

/**
 * Created by Martin De Girolamo on 05/04/2019.
 */

data class StoresDto(
    val stores: List<Store>
) {
    companion object {
        fun empty() = StoresDto(emptyList())
    }
}