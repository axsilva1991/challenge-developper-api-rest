package br.com.bank.digital_account.`output-boundary`

import br.com.bank.digital_account.`output-boundary`.dto.request.AccountOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.DepositOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.TransferOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.WithdrawOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.response.AccountOutputResDTO
import br.com.bank.digital_account.`output-boundary`.dto.response.EntriesOutputResDTO
import java.time.LocalDateTime

interface AccountRepository {
    fun create(accountOutputReqDTO: AccountOutputReqDTO)
    fun changeSituation(code: Long, active: Boolean): AccountOutputResDTO
    fun created(documentNumber: String): Boolean
    fun findBy(documentNumber: String): AccountOutputResDTO
    fun depoist(depositOutputReqDTO:DepositOutputReqDTO)
    fun withdraw(withdrawOutputReqDTO:WithdrawOutputReqDTO)
    fun getBalanceBy(code: Long):Double
    fun transfer(transferOutputReqDTO: TransferOutputReqDTO)
    fun statement(code: Long, initalDate:LocalDateTime, endDateTime: LocalDateTime): List<EntriesOutputResDTO>
    fun cancel(code: Long): AccountOutputResDTO
}