package br.com.bank.digital_account.domain

import br.com.bank.digital_account.domain.constants.Constants.Companion.AMMOUNTISNOTOK
import br.com.bank.digital_account.domain.constants.Constants.Companion.INSUFFICIENT_BALANCE
import br.com.bank.digital_account.domain.exception.TransactionInvalidException
import br.com.bank.digital_account.domain.exception.TransferDefautlException

class Transfer (
    val accountBalance: Double,
    val money: Double,
) {
    private var balance: Double = accountBalance

    private fun validate() {
        if (this.money <= 0) {
            throw TransactionInvalidException(AMMOUNTISNOTOK)
        }
    }

    private fun withdrawMoney(value: Double): Double{
        if (this.balance < value)
            throw TransferDefautlException(INSUFFICIENT_BALANCE)
        this.balance -= value;
        return this.balance
    }

    fun newBalance(value: Double):Double {
        validate()
        return this.withdrawMoney(value)
    }
}