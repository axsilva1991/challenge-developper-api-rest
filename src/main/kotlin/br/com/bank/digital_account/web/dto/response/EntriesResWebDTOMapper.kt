package br.com.bank.digital_account.web.dto.response

import br.com.bank.digital_account.`input-boundary`.dto.response.EntriesInputResDTO

class EntriesResWebDTOMapper {
    companion object {
        fun List<EntriesInputResDTO>.mapperFrom(): List<EntriesResWebDTO> {
            return this.map {
                EntriesResWebDTO(
                    type = it.type,
                    value = it.value,
                    operation_date = it.operation_date
                )
            }
        }
    }
}