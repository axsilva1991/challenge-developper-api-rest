package br.com.bank.digital_account.`input-boundary`

import br.com.bank.digital_account.`input-boundary`.dto.request.DepositInputReqDTO
import br.com.bank.digital_account.`input-boundary`.dto.request.TransferInputReqDTO
import br.com.bank.digital_account.`input-boundary`.dto.request.WithdrawInputReqDTO
import br.com.bank.digital_account.`input-boundary`.dto.response.AccountInputResDTO
import br.com.bank.digital_account.`input-boundary`.dto.response.EntriesInputResDTO

interface AccountInput {
    fun open(name:String, documentCode:String)
    fun findBy(documentCode: String): AccountInputResDTO
    fun deposit(depositInputReqDTO:DepositInputReqDTO)
    fun withdraw(withdrawInputReqDTO:WithdrawInputReqDTO)
    fun transfer(transferInputReqDTO:TransferInputReqDTO)
    fun situationStatus(code: Long, active: Boolean): AccountInputResDTO
    fun statement(code: Long, days: Long): List<EntriesInputResDTO>
    fun cancel(code: Long): AccountInputResDTO
}