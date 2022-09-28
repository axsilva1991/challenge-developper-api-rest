package br.com.bank.digital_account.repository.exception

class WithdrawException(override val message: String?) : RuntimeException(message)