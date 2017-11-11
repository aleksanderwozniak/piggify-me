package com.olq.piggifyme.mainscreen

import com.olq.piggifyme.BasePresenter
import com.olq.piggifyme.BaseView

/**
 * Created by olq on 11.11.17.
 */
interface MainScreenContract {

    interface View : BaseView<Presenter> {
        fun showNewItemDialog(dialogType: DialogType)
    }

    interface Presenter : BasePresenter {
        fun validateUserInput(amout: String)
    }
}