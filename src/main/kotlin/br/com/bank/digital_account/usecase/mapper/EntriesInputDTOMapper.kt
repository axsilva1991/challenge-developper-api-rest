package br.com.bank.digital_account.usecase.mapper

import br.com.bank.digital_account.`input-boundary`.dto.response.EntriesInputResDTO
import br.com.bank.digital_account.`output-boundary`.dto.response.EntriesOutputResDTO

class EntriesInputDTOMapper {
    companion object {
        fun List<EntriesOutputResDTO>.mapperFrom(): List<EntriesInputResDTO> {
            return this.map {
                EntriesInputResDTO(
                    type = it.type,
                    value = it.value,
                    operation_date = it.operation_date
                )
            }
        }
    }
}