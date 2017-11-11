package com.olq.piggifyme.mainscreen

/**
 * Created by olq on 11.11.17.
 */
class MainPresenter(val mainView: MainScreenContract.View)
    : MainScreenContract.Presenter {

    init {
        mainView.presenter = this
    }


    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onFABItemClick(dialogType: DialogType) {
        mainView.showNewItemDialog(dialogType)
    }


    override fun onNewItemAdded(dialogType: DialogType, amount: String) {
        val newValue = validateUserInput(amount)

        updateView(dialogType, newValue)
    }

    private fun validateUserInput(amount: String): Int {
        return if (amount.isEmpty()) 0 else amount.toInt()
    }

    private fun updateView(dialogType: DialogType, value: Int) {
        when (dialogType) {
            DialogType.DIALOG_INCOME -> {
                mainView.updateIncomeView(value)
            }

            DialogType.DIALOG_EXPENSE -> {
                mainView.updateExpenseView(value)
            }
        }
    }
}