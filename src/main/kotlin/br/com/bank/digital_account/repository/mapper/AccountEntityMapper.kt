package br.com.bank.digital_account.repository.mapper

import br.com.bank.digital_account.`output-boundary`.dto.request.AccountOutputReqDTO
import br.com.bank.digital_account.repository.entity.AccountEntity

class AccountEntityMapper {
    companion object {
        fun AccountOutputReqDTO.mapperFrom(): AccountEntity {
            return AccountEntity(
                agency= this.agency,
                documentCode = this.holder.documentCode,
                name = this.holder.name,
                balance = this.balance
            )
        }
    }
}