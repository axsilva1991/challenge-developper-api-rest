package br.com.bank.digital_account.domain

import br.com.bank.digital_account.domain.exception.DocumentInvalidException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class HolderTest {

    @Test
    fun `when document is valid`() {
        val holder = Holder(documentCode = "49052645027", name = "Test success")
        assertDoesNotThrow { holder.validate() }
    }

    @Test
    fun `when document throws business exception on validate`() {
        val holder = Holder(documentCode = "49052645025", name = "Test fail")
        assertThrows<DocumentInvalidException> { holder.validate() }
    }
}