package br.com.bank.digital_account.usecase.mapper

import br.com.bank.digital_account.`input-boundary`.dto.request.TransferInputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.TransferOutputReqDTO

class TransferOutputReqDTOMapper {
    companion object{
        fun TransferInputReqDTO.mapperFrom(
            originNewBalance: Double
        ) = TransferOutputReqDTO(
            origin = this.origin,
            moneyTrasnsaction = this.money,
            originNewBalance = originNewBalance,
            destination = this.destination
        )
    }
}