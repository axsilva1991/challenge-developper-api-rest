package br.com.bank.digital_account.usecase.mapper

import br.com.bank.digital_account.`input-boundary`.dto.request.WithdrawInputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.WithdrawOutputReqDTO

class WithdrawOutputReqDTOMapper {
    companion object{
        fun WithdrawInputReqDTO.mapperFrom() :WithdrawOutputReqDTO{
            return WithdrawOutputReqDTO(this.accountCode,this.money)
        }
    }
}