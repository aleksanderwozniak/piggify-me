package com.olq.piggifyme.screens.details

import com.olq.piggifyme.BaseView

/**
 * Created by olq on 19.11.17.
 */

// Even though Income and Expense screens share the same functionality,
// I have decided to keep them separated for more code clarity.
//
// Their common behavior will be described in more generic DetailView interface
// (and possibly DetailPresenter in the future)
interface DetailView<T> : BaseView<T> {

    fun showDetailList(list: List<Pair<String, Int>>)
}