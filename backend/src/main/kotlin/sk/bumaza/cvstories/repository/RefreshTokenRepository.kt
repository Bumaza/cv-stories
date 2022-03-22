package sk.bumaza.cvstories.repository

import org.springframework.data.repository.CrudRepository
import sk.bumaza.cvstories.entity.managment.Account
import sk.bumaza.cvstories.entity.managment.RefreshToken
import java.util.*

interface RefreshTokenRepository: CrudRepository<RefreshToken, Long> {
    fun findById(id: Long?) : RefreshToken?
    fun findByToken(token: String) : Optional<RefreshToken>
    fun deleteByAccount(account: Account): Int
}