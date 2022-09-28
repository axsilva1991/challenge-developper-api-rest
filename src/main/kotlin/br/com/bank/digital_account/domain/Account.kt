package br.com.bank.digital_account.domain

class Account(
    val holder: Holder,
){
    //In this version we will provide the opening of an account with an initial balance is 100.00$ and accounts at our branch 1
    private val initialDeposit: Double = 100.00
    private val agency: Long = 1L

    fun getAgency():Long = agency
    fun getbalance():Double = initialDeposit
}