package br.com.bank.digital_account.usecase

import br.com.bank.digital_account.`input-boundary`.dto.request.DepositInputReqDTO
import br.com.bank.digital_account.`output-boundary`.AccountRepository
import br.com.bank.digital_account.domain.Account
import br.com.bank.digital_account.domain.Holder
import br.com.bank.digital_account.usecase.exception.BusinessExeption
import br.com.bank.digital_account.usecase.mapper.AccountOutputReqDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.usecase.mapper.DepositOutputReqDTOMapper.Companion.mapperFrom
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy


class AccountUseCaseTest {

    private val accountRepository: AccountRepository = mock(AccountRepository::class.java)
    private val accountUseCase: AccountUseCase = spy(AccountUseCase(accountRepository))

    @Test
    fun `when account open run ok`() {
        val document: String = "38657312875"
        val name: String = "name test"
        val account = Account(Holder(documentCode = document, name= name))
        doNothing().`when`(accountRepository).create(account.mapperFrom())
        `when`(accountRepository.created(document)).thenReturn(false)
        assertDoesNotThrow { accountUseCase.open(name = name, documentCode = document) }
        val inOrder = inOrder(accountRepository)
        inOrder.verify(accountRepository, Mockito.times(1)).created(document)
        inOrder.verify(accountRepository, Mockito.times(1)).create(account.mapperFrom())
    }

    @Test
    fun `when account open throws a exception of invalid document`() {
        val document: String = "38657312874"
        val name: String = "name test"
        val account = Account(Holder(documentCode = document, name= name))
        doNothing().`when`(accountRepository).create(account.mapperFrom())
        `when`(accountRepository.created(document)).thenReturn(false)
        assertThrows<BusinessExeption> { accountUseCase.open(name = name, documentCode = document) }
    }

    @Test
    fun `when account open throws a exception for account already exists`() {
        val document: String = "38657312874"
        val name: String = "name test"
        val account = Account(Holder(documentCode = document, name= name))
        doNothing().`when`(accountRepository).create(account.mapperFrom())
        assertThrows<BusinessExeption> { accountUseCase.open(name = name, documentCode = document) }
    }

    @Test
    fun `when deposit run ok`() {
        val depositInputReqDTO: DepositInputReqDTO = DepositInputReqDTO(accountCode = 1L, money = 1.0)
        doNothing().`when`(accountRepository).depoist(depositInputReqDTO.mapperFrom())
        assertDoesNotThrow { accountUseCase.deposit(depositInputReqDTO) }
    }

    @Test
    fun `when deposit return runtime exception`() {
        val depositInputReqDTO: DepositInputReqDTO = DepositInputReqDTO(accountCode = 1L, money = 1.0)
        `when`(accountRepository.depoist(depositInputReqDTO.mapperFrom())).thenThrow(RuntimeException::class.java)
        assertThrows<RuntimeException>  { accountUseCase.deposit(depositInputReqDTO) }
    }

}