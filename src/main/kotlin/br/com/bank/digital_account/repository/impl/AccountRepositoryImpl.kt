package br.com.bank.digital_account.repository.impl

import br.com.bank.digital_account.`output-boundary`.AccountRepository
import br.com.bank.digital_account.`output-boundary`.dto.request.AccountOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.DepositOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.TransferOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.WithdrawOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.response.AccountOutputResDTO
import br.com.bank.digital_account.`output-boundary`.dto.response.EntriesOutputResDTO
import br.com.bank.digital_account.repository.`interface`.AccountRepositoryInterface
import br.com.bank.digital_account.repository.`interface`.StatementRepositoryInterface
import br.com.bank.digital_account.repository.entity.AccountEntity
import br.com.bank.digital_account.repository.exception.WithdrawException
import br.com.bank.digital_account.repository.mapper.AccountEntityMapper.Companion.mapperFrom
import br.com.bank.digital_account.repository.mapper.AccountOutputResDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.repository.mapper.EntriesOutputResDTOMapper.Companion.mapperFrom
import br.com.bank.digital_account.repository.mapper.StatementEntityMapper.Companion.statementEntityDepositBuild
import br.com.bank.digital_account.repository.mapper.StatementEntityMapper.Companion.statementEntityOpenAccountBuild
import br.com.bank.digital_account.repository.mapper.StatementEntityMapper.Companion.statementEntityWithdrawBuild
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.NoSuchElementException
import kotlin.RuntimeException

@Service
class AccountRepositoryImpl(
    private val accountRepositoryInterface: AccountRepositoryInterface,
    private val statementRepositoryInterface: StatementRepositoryInterface
) : AccountRepository {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun create(accountOutputReqDTO: AccountOutputReqDTO) {
        log.info("AccountRepositoryImpl.create() = AccountOutputDTO:-> {}", accountOutputReqDTO)
        try {
            val account = accountRepositoryInterface.save(accountOutputReqDTO.mapperFrom())
            statementRepositoryInterface.save(account.statementEntityOpenAccountBuild(account.balance))
        } catch (ex: RuntimeException) {
            log.error("AccountRepositoryImpl.create() = error :-> {}", ex)
            throw ex
        }
    }

    override fun changeSituation(code: Long, active: Boolean): AccountOutputResDTO {
        log.info("AccountRepositoryImpl.changeSituation() = code:-> {}", code, " active:-> {}", active)
        val account = accountRepositoryInterface.findById(code).get()
        if(active){
            account.descriptionStatus="unlocked"
        }else{
            account.descriptionStatus="locked"
        }
        account.active = active
        account.situationDate = LocalDateTime.now()
        return accountRepositoryInterface.save(account).mapperFrom()
    }

    override fun cancel(code: Long): AccountOutputResDTO {
        log.info("AccountRepositoryImpl.changeSituation() = code:-> {}", code)
        val account = accountRepositoryInterface.findById(code).get()
        account.descriptionStatus="canceled"
        account.active = false
        account.situationDate = LocalDateTime.now()
        return accountRepositoryInterface.save(account).mapperFrom()
    }

    override fun created(documentNumber: String): Boolean {
        log.info("AccountRepositoryImpl.created() = documentNumber:-> {}", documentNumber)
        try {
            accountRepositoryInterface.findByDocumentCode(documentNumber).first()
            log.error("AccountRepositoryImpl.created() = account already exists")
            return true
        } catch (ex: NoSuchElementException) {
            log.info("AccountRepositoryImpl.created() = not found account for this doccument")
            return false
        } catch (ex: RuntimeException) {
            log.error("AccountRepositoryImpl.created() = error :-> {}", ex)
            throw ex
        }

    }

    override fun findBy(documentNumber: String): AccountOutputResDTO {
        try {
            log.info("AccountRepositoryImpl.findBy() = documentNumber:-> {}", documentNumber)
            return accountRepositoryInterface.findByDocumentCode(documentNumber).first().mapperFrom()
        } catch (ex: RuntimeException) {
            log.error("AccountRepositoryImpl.findBy() = error :-> {}", ex)
            throw ex
        }
    }

    override fun depoist(depositOutputReqDTO: DepositOutputReqDTO) {
        log.info("AccountRepositoryImpl.depoist() = depositOutputReqDTO:-> {}", depositOutputReqDTO)
        val account = accountRepositoryInterface.findByIdAndActive(depositOutputReqDTO.accountCode, true).first()
        account.balance += depositOutputReqDTO.money
        accountRepositoryInterface.save(account)
        statementRepositoryInterface.save(
            account.statementEntityDepositBuild(depositOutputReqDTO.money, "deposit")
        )
    }

    override fun withdraw(withdrawOutputReqDTO: WithdrawOutputReqDTO) {
        log.info("AccountRepositoryImpl.withdraw() = withdrawOutputReqDTO:-> {}", withdrawOutputReqDTO)
        val account = accountRepositoryInterface.findByIdAndActive(withdrawOutputReqDTO.accountCode, true).first()
        account.balance -= withdrawOutputReqDTO.money
        if (account.balance < 0) {
            throw WithdrawException("balance is insuficient for this operation")
        }
        accountRepositoryInterface.save(account)
        statementRepositoryInterface.save(
            account.statementEntityDepositBuild(withdrawOutputReqDTO.money, "withdraw")
        )
    }

    override fun getBalanceBy(code: Long): Double {
        log.info("AccountRepositoryImpl.getBalanceBy() = code:-> {}", code)
        return accountRepositoryInterface.findByIdAndActive(code, true).first().balance
    }

    override fun transfer(transferOutputReqDTO: TransferOutputReqDTO) {
        log.info("AccountRepositoryImpl.withdraw() = WithdrawOutputReqDTO:-> {}", transferOutputReqDTO)
        val account = accountRepositoryInterface.findByIdAndActive(transferOutputReqDTO.origin, true).first()
        val debit = account.balance - transferOutputReqDTO.originNewBalance
        account.balance = transferOutputReqDTO.originNewBalance
        accountRepositoryInterface.save(account)
        statementRepositoryInterface.save(
            account.statementEntityWithdrawBuild(debit)
        )
        val accountDestination =
            accountRepositoryInterface.findByIdAndActive(transferOutputReqDTO.destination, true).first()
        depoist(accountDestination, "transfer", transferOutputReqDTO.moneyTrasnsaction)
    }

    override fun statement(
        code: Long,
        initalDate: LocalDateTime,
        endDateTime: LocalDateTime
    ): List<EntriesOutputResDTO> {
        try {
            log.info("AccountRepositoryImpl.statement() = code:-> {}", code)
            return statementRepositoryInterface.findByOperationDateBetweenAndDestinationAccountId(
                initalDate,
                endDateTime,
                code
            ).mapperFrom()
        } catch (ex: RuntimeException) {
            log.error("AccountRepositoryImpl.findBy() = error :-> {}", ex)
            throw ex
        }
    }

    private fun depoist(accountDestination: AccountEntity, type: String, moneyTrasnsaction: Double) {
        log.info(
            "AccountRepositoryImpl.depoist() = AccountEntity:-> {}",
            accountDestination,
            "= type:-> {}",
            type,
            "= moneyTrasnsaction:-> {}",
            moneyTrasnsaction
        )
        accountDestination.balance += moneyTrasnsaction
        accountRepositoryInterface.save(accountDestination)
        statementRepositoryInterface.save(
            accountDestination.statementEntityDepositBuild(moneyTrasnsaction, type)
        )
    }
}