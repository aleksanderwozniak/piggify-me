package com.olq.piggifyme.injector

import android.content.Context
import com.olq.piggifyme.data.database.LocalDataSource
import com.olq.piggifyme.data.database.PiggifyDbHelper
import com.olq.piggifyme.data.Model

/**
 * Created by olq on 11.11.17.
 */
//TODO: to be swapped for Dagger2
object Injector {

    fun provideModel(ctx: Context): Model {
        checkNotNull(ctx)
        return Model.getInstance(LocalDataSource(PiggifyDbHelper.getInstance(ctx)))
    }
}