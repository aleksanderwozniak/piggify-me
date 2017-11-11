package com.olq.piggifyme.injector

import com.olq.piggifyme.mainscreen.Model

/**
 * Created by olq on 11.11.17.
 */
//TODO: to be swapped for Dagger2
object Injector {

    private val model = Model()

    fun provideModel(): Model {
        return model
    }
}
