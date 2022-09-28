package br.com.bank.digital_account.web.mapper

import br.com.bank.digital_account.`input-boundary`.dto.request.WithdrawInputReqDTO
import br.com.bank.digital_account.web.dto.request.WithdrawReqWebDTO

class WithdrawInputReqDTOMapper {
    companion object {
        fun WithdrawReqWebDTO.mapperFrom(): WithdrawInputReqDTO {
            return WithdrawInputReqDTO(accountCode = this.accountCode, money = this.money)
        }
    }
}
