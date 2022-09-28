package br.com.bank.digital_account.web.dto.request

data class DepositReqWebDTO(
    val accountCode: Long,
    val money : Double
)