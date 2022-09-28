package br.com.bank.digital_account.domain

import br.com.bank.digital_account.domain.exception.TransactionInvalidException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TransactionTest{

    @Test
    fun `when deposit is valid`() {
        val transaction =  Transaction(money = 2.0, accountCode = 1)
        assertDoesNotThrow { transaction.validate() }
    }

    @Test
    fun `when deposit throws business exception on validate money`() {
        val transaction =  Transaction(money = -2.0, accountCode = 1)
        assertThrows<TransactionInvalidException> { transaction.validate() }
    }

    @Test
    fun `when deposit throws business exception on validate accountCode`() {
        val transaction =  Transaction(money = 2.0, accountCode = -1)
        assertThrows<TransactionInvalidException> { transaction.validate() }
    }

}