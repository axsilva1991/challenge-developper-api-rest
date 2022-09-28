package br.com.bank.digital_account.usecase.mapper

import br.com.bank.digital_account.`input-boundary`.dto.request.DepositInputReqDTO
import br.com.bank.digital_account.`output-boundary`.dto.request.DepositOutputReqDTO

class DepositOutputReqDTOMapper {
    companion object{
        fun DepositInputReqDTO.mapperFrom() :DepositOutputReqDTO{
            return DepositOutputReqDTO(this.accountCode,this.money)
        }
    }
}