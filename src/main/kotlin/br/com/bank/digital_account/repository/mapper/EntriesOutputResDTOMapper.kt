package br.com.bank.digital_account.repository.mapper

import br.com.bank.digital_account.`output-boundary`.dto.response.EntriesOutputResDTO
import br.com.bank.digital_account.repository.entity.StatementEntity

class EntriesOutputResDTOMapper{
    companion object{
        fun List<StatementEntity>.mapperFrom(): List<EntriesOutputResDTO> {
            return this.map {
                EntriesOutputResDTO(
                    type = it.type,
                    value = it.value,
                    operation_date = it.operationDate
                )
            }
        }
    }
}