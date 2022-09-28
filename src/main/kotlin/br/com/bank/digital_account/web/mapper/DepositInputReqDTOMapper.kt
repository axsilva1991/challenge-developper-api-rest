package br.com.bank.digital_account.web.mapper

import br.com.bank.digital_account.`input-boundary`.dto.request.DepositInputReqDTO
import br.com.bank.digital_account.web.dto.request.DepositReqWebDTO

class DepositInputReqDTOMapper {
    companion object {
        fun DepositReqWebDTO.mapperFrom(): DepositInputReqDTO {
            return DepositInputReqDTO(accountCode = this.accountCode, money = this.money)
        }
    }
}
