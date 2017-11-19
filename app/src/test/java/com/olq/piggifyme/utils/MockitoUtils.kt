package com.olq.piggifyme.utils

import org.mockito.Mockito

/**
 * Created by olq on 19.11.17.
 */

object MockitoUtils {

    // quickfix functions for Kotlin's null-safety testing issue
    fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    fun <T> uninitialized(): T = null as T
}