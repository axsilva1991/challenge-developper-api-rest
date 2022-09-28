package br.com.bank.digital_account.usecase

import br.com.bank.digital_account.`input-boundary`.AccountInput
import br.com.bank.digital_account.`input-boundary`.dto.request.DepositInputReqDTO
import br.com.bank.digital_account.`input-boundary`.dto.request.TransferInputReqDTO
import br.com.bank.digital_account.`input-boundary`.dto.request.WithdrawInputReqDTO
import br.com.bank.digital_account.`input-boundary`.dto.response.AccountInputResDTO
import br.com.bank.digital_account.`input-boundary`.dto.response.EntriesInputResDTO
import br.com.bank.digital_account.`output-boundary`.AccountRepository
import br.com.bank.digital_account.domain.Account
import br.com.bank.digital_account.domain.Holder
import br.com.bank.digital_account.domain.Statement
import br.com.bank.digital_account.domain.Transfer
import br.com.bank.digital_account.domain.Withdraw
import br.com.bank.digital_account.domain.exception.TransactionInvalidException
import br.com.bank.digital_account.domain.exception.DocumentInvalidException
import br.com.bank.digital_account.domain.exception.PediodInvalidException
import br.com.bank.digital_account.repository.exception.WithdrawException
import br.com.bank.digital_account.usecase.constants.UseCaseConstants.Companion.ACCOUNTALREADYEXISTS
import br.com.bank.digital_account.usecase.constants.UseCaseConstants.Companion.ACCOUNTISNOTACTIVE
import br.com.bank.digital_account.usecase.exception.BusinessExeption
import br.com.bank.digital_account.usecase.exception.NotFoundExeption
import br.com.bank.digital_account.usecase.mapper.AccountInputDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.usecase.mapper.AccountOutputReqDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.usecase.mapper.TransactionMapper.Companion.mapperFromDomain
import br.com.bank.digital_account.usecase.mapper.DepositOutputReqDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.usecase.mapper.EntriesInputDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.usecase.mapper.TransferOutputReqDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.usecase.mapper.WithdrawOutputReqDTOMapper.Companion.mapperFrom
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.util.NoSuchElementException

@Service
class AccountUseCase(
    private val accountRepository: AccountRepository
) : AccountInput {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun open(name: String, documentCode: String) {
        try {
            val holder = Holder(documentCode = documentCode, name = name)
            holder.validate()
            if (accountRepository.created(documentCode)) {
                throw BusinessExeption(ACCOUNTALREADYEXISTS)
            }
            accountRepository.create(Account(holder).mapperFrom())
        } catch (ex: DocumentInvalidException) {
            log.error("AccountUseCase.open() Exception", ex)
            throw BusinessExeption(ex.message)
        }

    }

    override fun findBy(documentCode: String): AccountInputResDTO {
        try {
            return accountRepository.findBy(documentCode).mapperFrom()
        } catch (ex: NoSuchElementException) {
            throw NotFoundExeption(ex.message)
        }
    }

    override fun deposit(depositInputReqDTO: DepositInputReqDTO) {
        try {
            depositInputReqDTO.mapperFromDomain().validate()
            accountRepository.depoist(depositInputReqDTO.mapperFrom())
        } catch (ex: TransactionInvalidException) {
            log.error("AccountUseCase.deposit() Exception", ex)
            throw BusinessExeption(ex.message)
        } catch (ex: NoSuchElementException) {
            throw BusinessExeption(ACCOUNTISNOTACTIVE)
        } catch (ex: RuntimeException) {
            throw ex
        }
    }

    override fun withdraw(withdrawInputReqDTO: WithdrawInputReqDTO) {
        try {
            Withdraw(withdrawInputReqDTO.money).validate()
            withdrawInputReqDTO.mapperFromDomain().validate()
            accountRepository.withdraw(withdrawInputReqDTO.mapperFrom())
        } catch (ex: TransactionInvalidException) {
            log.error("AccountUseCase.withdraw() Exception", ex)
            throw BusinessExeption(ex.message)
        } catch (ex: WithdrawException) {
            log.error("AccountUseCase.withdraw() Exception", ex)
            throw BusinessExeption(ex.message)
        } catch (ex: NoSuchElementException) {
            throw BusinessExeption(ACCOUNTISNOTACTIVE)
        }
    }

    override fun transfer(transferInputReqDTO: TransferInputReqDTO) {
        try {
            val originNewBalance = buildTransfer(
                transferInputReqDTO,
                accountRepository.getBalanceBy(transferInputReqDTO.origin)
            ).newBalance(value = transferInputReqDTO.money)
            accountRepository.transfer(transferInputReqDTO.mapperFrom(originNewBalance))
        } catch (ex: NoSuchElementException) {
            throw BusinessExeption(ACCOUNTISNOTACTIVE)
        }
    }

    override fun situationStatus(code: Long, active: Boolean): AccountInputResDTO {
        try {
            return accountRepository.changeSituation(code, active).mapperFrom()
        } catch (ex: NoSuchElementException) {
            throw NotFoundExeption(ex.message)
        }
    }

    override fun cancel(code: Long): AccountInputResDTO {
        try {
            return accountRepository.cancel(code).mapperFrom()
        } catch (ex: NoSuchElementException) {
            throw NotFoundExeption(ex.message)
        }
    }

    override fun statement(code: Long, days: Long): List<EntriesInputResDTO> {
        try {
            return accountRepository.statement(code, Statement(days).getDiferenceDate(), LocalDateTime.now())
                .mapperFrom()
        } catch (ex: PediodInvalidException) {
            log.error("AccountUseCase.statement() Exception", ex)
            throw BusinessExeption(ex.message)
        } catch (ex: NoSuchElementException) {
            throw NotFoundExeption(ex.message)
        }
    }

    private fun buildTransfer(transferInputReqDTO: TransferInputReqDTO, accountBalance: Double) =
        Transfer(
            money = transferInputReqDTO.money,
            accountBalance = accountBalance
        )

}