package br.com.bank.digital_account.domain

import br.com.bank.digital_account.domain.constants.Constants.Companion.DOCUMENTINVALIDMESSAGE
import br.com.bank.digital_account.domain.exception.DocumentInvalidException
import br.com.bank.digital_account.domain.util.CPFUtil

open class Holder(
    val documentCode: String,
    val name: String
) {

    fun validate() {
        if (!CPFUtil.validate(this.documentCode))
            throw DocumentInvalidException(DOCUMENTINVALIDMESSAGE)
    }
}