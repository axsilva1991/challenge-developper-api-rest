package br.com.bank.digital_account.domain.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CPFUtilTest{
    @Test
    fun WhenCPFIsValid(){
        Assertions.assertTrue(CPFUtil.validate("38657312875"))
    }
    @Test
    fun WhenCPFIsInvalid(){
        Assertions.assertFalse(CPFUtil.validate("38657312874"))
    }

}