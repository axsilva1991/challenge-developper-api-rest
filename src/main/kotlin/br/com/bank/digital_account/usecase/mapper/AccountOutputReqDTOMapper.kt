package br.com.bank.digital_account.usecase.mapper

import br.com.bank.digital_account.`output-boundary`.dto.request.AccountOutputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.HolderOutputReqDTO
import br.com.bank.digital_account.domain.Account

class AccountOutputReqDTOMapper {
    companion object {
        fun Account.mapperFrom(): AccountOutputReqDTO {
            return AccountOutputReqDTO(
                holder = HolderOutputReqDTO(documentCode = this.holder.documentCode, name = this.holder.name),
                agency = this.getAgency(),
                balance= this.getbalance()
            )
        }

    }
}