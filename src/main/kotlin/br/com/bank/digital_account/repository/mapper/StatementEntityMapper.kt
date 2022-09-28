package br.com.bank.digital_account.repository.mapper

import br.com.bank.digital_account.repository.entity.AccountEntity
import br.com.bank.digital_account.repository.entity.StatementEntity

class StatementEntityMapper {
    companion object{
        fun AccountEntity.statementEntityDepositBuild(
            money: Double
        ) = StatementEntity(
            destinationAccount = this,
            balance = this.balance,
            value = money,
            type = "deposit"
        )

        fun AccountEntity.statementEntityDepositBuild(
            money: Double,
            type:String
        ) = StatementEntity(
            destinationAccount = this,
            balance = this.balance,
            value = money,
            type = type
        )

        fun AccountEntity.statementEntityOpenAccountBuild(
            money: Double
        ) = StatementEntity(
            destinationAccount = this,
            balance = this.balance,
            value = money,
            type = "open_account"
        )

        fun AccountEntity.statementEntityWithdrawBuild(
            money: Double
        ) = StatementEntity(
            destinationAccount = this,
            balance = this.balance,
            value = money,
            type = "withdraw"
        )
    }
}