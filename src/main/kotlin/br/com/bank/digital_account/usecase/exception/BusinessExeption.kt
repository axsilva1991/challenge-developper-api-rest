package br.com.bank.digital_account.usecase.exception

class BusinessExeption(override val message: String?) : RuntimeException(message)
