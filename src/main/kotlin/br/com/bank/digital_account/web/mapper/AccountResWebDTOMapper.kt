package br.com.bank.digital_account.web.mapper

import br.com.bank.digital_account.`input-boundary`.dto.response.AccountInputResDTO
import br.com.bank.digital_account.web.dto.response.AccountResWebDTO
import br.com.bank.digital_account.web.dto.response.HolderResWebDTO

class AccountResWebDTOMapper {
    companion object {
        fun AccountInputResDTO.mapperFrom(): AccountResWebDTO {
            return AccountResWebDTO(
                code = this.code,
                holder = HolderResWebDTO(
                    documentCode = this.holder.documentCode,
                    name = this.holder.name
                ),
                agency = this.agency,
                balance = this.balance,
                situationDate = situationDate,
                active = this.active
            )
        }
    }
}