package br.com.bank.digital_account.usecase.mapper

import br.com.bank.digital_account.`input-boundary`.dto.response.AccountInputResDTO
import br.com.bank.digital_account.`input-boundary`.dto.response.HolderInputDTO
import br.com.bank.digital_account.`output-boundary`.dto.response.AccountOutputResDTO

class AccountInputDTOMapper {
    companion object {
        fun AccountOutputResDTO.mapperFrom():AccountInputResDTO{
            return AccountInputResDTO(
                code = this.code,
                holder = HolderInputDTO(documentCode = this.holder.documentCode, name = this.holder.name),
                balance = this.balance,
                situationDate = situationDate,
                descriptionStatus =this.descriptionStatus,
                agency = agency,
                active = this.active
            )
        }
    }
}