package com.ionnt.rocketpocket.commons.utils

/**
 * Created by Martin De Girolamo on 08/04/2019.
 * Base Class for handling errors/failures/exceptions.
 */

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object NoDataAvailable: Failure()

    /**  Extend this class for specific failures. */
    abstract class FeatureFailure: Failure()
}
