package br.com.bank.digital_account.repository.impl

import br.com.bank.digital_account.`output-boundary`.dto.request.AccountOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.HolderOutputReqDTO
import br.com.bank.digital_account.repository.`interface`.AccountRepositoryInterface
import br.com.bank.digital_account.repository.`interface`.StatementRepositoryInterface
import br.com.bank.digital_account.repository.entity.AccountEntity
import br.com.bank.digital_account.repository.mapper.AccountEntityMapper.Companion.mapperFrom
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy


class AccountRepositoryImplTest {
    private val accountRepositoryInterface: AccountRepositoryInterface = mock(AccountRepositoryInterface::class.java)
    private val statementRepositoryInterface: StatementRepositoryInterface =
        mock(StatementRepositoryInterface::class.java)
    private val accountRepositoryImpl: AccountRepositoryImpl =
        spy(AccountRepositoryImpl(accountRepositoryInterface, statementRepositoryInterface))

    private fun <T> anyObject(): T {
        return Mockito.any()
    }

    @Test
    fun `when account create run ok`() {
        val accountOutputDTO = AccountOutputReqDTO(
            holder = HolderOutputReqDTO(documentCode = "51196658021", name = "Nome Teste"),
            agency = 1L,
            balance = 100.0
        )
        val accountEntity = accountOutputDTO.mapperFrom()

        `when`(accountRepositoryInterface.save(anyObject())).thenReturn(accountEntity)
        assertDoesNotThrow { accountRepositoryImpl.create(accountOutputDTO) }
    }

    @Test
    fun `when account has created`() {
        val documentCode = "51196658021"
        `when`(accountRepositoryInterface.findByDocumentCode(documentCode)).thenReturn(listOf(AccountEntity()))
        assert(accountRepositoryImpl.created(documentCode))
    }

    @Test
    fun `when account hasn't created`() {
        val documentCode = "51196658021"
        `when`(accountRepositoryInterface.findByDocumentCode(documentCode)).thenReturn(emptyList())
        assertFalse(accountRepositoryImpl.created(documentCode))
    }

    @Test
    fun `when account created return runtime exception`() {
        val documentCode = "51196658021"
        `when`(accountRepositoryInterface.findByDocumentCode(documentCode)).thenThrow(RuntimeException::class.java)
        assertThrows<RuntimeException> { accountRepositoryImpl.created(documentCode) }
    }
}