package br.com.bank.digital_account.usecase.mapper

import br.com.bank.digital_account.`input-boundary`.dto.request.DepositInputReqDTO
import br.com.bank.digital_account.`input-boundary`.dto.request.WithdrawInputReqDTO
import br.com.bank.digital_account.domain.Transaction

class TransactionMapper {
    companion object {
        fun DepositInputReqDTO.mapperFromDomain(): Transaction =
            Transaction(accountCode = this.accountCode, money = this.money)
        fun WithdrawInputReqDTO.mapperFromDomain(): Transaction =
            Transaction(accountCode = this.accountCode, money = this.money)
    }
}