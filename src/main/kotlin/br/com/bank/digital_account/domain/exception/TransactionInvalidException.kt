package br.com.bank.digital_account.domain.exception

import java.lang.RuntimeException

class TransactionInvalidException(override val message: String?) : RuntimeException(message)