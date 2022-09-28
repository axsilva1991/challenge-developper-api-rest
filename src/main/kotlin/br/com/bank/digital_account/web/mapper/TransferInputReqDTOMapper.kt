package br.com.bank.digital_account.web.mapper

import br.com.bank.digital_account.`input-boundary`.dto.request.TransferInputReqDTO
import br.com.bank.digital_account.web.dto.request.TransferReqWebDTO

class TransferInputReqDTOMapper {
    companion object{
        fun TransferReqWebDTO.mapperFrom(): TransferInputReqDTO{
            return TransferInputReqDTO(
                origin = this.origin,
                destination = this.destination,
                money = this.money
            )
        }
    }
}