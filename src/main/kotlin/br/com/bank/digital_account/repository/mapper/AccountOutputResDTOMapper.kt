package br.com.bank.digital_account.repository.mapper

import br.com.bank.digital_account.`output-boundary`.dto.response.AccountOutputResDTO
import br.com.bank.digital_account.`output-boundary`.dto.response.HolderOutputResDTO
import br.com.bank.digital_account.repository.entity.AccountEntity

class AccountOutputResDTOMapper {
    companion object {
        fun AccountEntity.mapperFrom(): AccountOutputResDTO {
            return AccountOutputResDTO(
                code = this.id,
                holder = HolderOutputResDTO(
                    documentCode = this.documentCode,
                    name = this.name
                ),
                balance = balance,
                agency = this.agency,
                situationDate = this.situationDate,
                active = this.active,
                descriptionStatus = this.descriptionStatus
            )
        }
    }
}