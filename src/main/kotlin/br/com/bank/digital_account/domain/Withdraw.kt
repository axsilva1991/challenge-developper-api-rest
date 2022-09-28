package br.com.bank.digital_account.domain

import br.com.bank.digital_account.domain.constants.Constants.Companion.VALUEISNOTALLOWED
import br.com.bank.digital_account.domain.exception.TransactionInvalidException

class Withdraw(val money: Double) {
    fun validate() {
        if (money>2000)
            throw TransactionInvalidException(VALUEISNOTALLOWED)
    }
}