package br.com.bank.digital_account.web.dto.request

data class WithdrawReqWebDTO(
    val accountCode: Long,
    val money : Double
)