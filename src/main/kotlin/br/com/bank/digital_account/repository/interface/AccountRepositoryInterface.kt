package br.com.bank.digital_account.repository.`interface`

import br.com.bank.digital_account.repository.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepositoryInterface : JpaRepository<AccountEntity, Long> {
    fun save(accountEntity: AccountEntity):AccountEntity
    fun findByDocumentCode(documentCode: String): List<AccountEntity>
    fun findByIdAndActive(id: Long, active: Boolean): List<AccountEntity>
}