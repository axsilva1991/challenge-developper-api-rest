package br.com.bank.digital_account.domain

import br.com.bank.digital_account.domain.constants.Constants.Companion.PERIODINVALID
import br.com.bank.digital_account.domain.exception.PediodInvalidException
import java.time.LocalDateTime

class Statement(val days: Long) {
    fun getDiferenceDate(): LocalDateTime {
        if(days<=30){
            val today = LocalDateTime.now()
            return today.minusDays(days);
        }
        throw PediodInvalidException(PERIODINVALID)
    }

}