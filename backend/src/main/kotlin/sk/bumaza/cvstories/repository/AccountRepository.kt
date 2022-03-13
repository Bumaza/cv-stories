package sk.bumaza.cvstories.repository

import org.springframework.data.repository.CrudRepository
import sk.bumaza.cvstories.entity.managment.Account
import java.util.*

interface AccountRepository : CrudRepository<Account, Long> {
    fun findByUsername(username: String?) : Optional<Account>?
    fun findByEmail(email: String?) : Optional<Account>
    fun existsByUsername(nickName: String?) : Boolean?
    fun existsByEmail(email: String?): Boolean?
}