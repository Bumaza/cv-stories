package sk.bumaza.cvstories.repository

import org.springframework.data.repository.CrudRepository
import sk.bumaza.cvstories.entity.managment.ResetToken
import java.util.*

interface ResetTokenRepository : CrudRepository<ResetToken, Long> {
    fun existsByToken(token: String?) : Boolean
    fun findByToken(token: String?) : Optional<ResetToken>
}