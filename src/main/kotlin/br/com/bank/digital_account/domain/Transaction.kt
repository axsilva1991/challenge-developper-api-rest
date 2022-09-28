package br.com.bank.digital_account.domain

import br.com.bank.digital_account.domain.constants.Constants.Companion.ACCOUNTCODEISNOTOK
import br.com.bank.digital_account.domain.constants.Constants.Companion.AMMOUNTTRANSACTIONISNOTOK
import br.com.bank.digital_account.domain.exception.TransactionInvalidException

class Transaction(
    val accountCode: Long,
    val money: Double
) {
    fun validate() {
        validateMoney()
        validateAccount()
    }

    private fun validateMoney() {
        if (this.money <= 0) {
            throw TransactionInvalidException(AMMOUNTTRANSACTIONISNOTOK)
        }
    }

    private fun validateAccount() {
        if (this.accountCode <= 0) {
            throw TransactionInvalidException(ACCOUNTCODEISNOTOK)
        }
    }
}