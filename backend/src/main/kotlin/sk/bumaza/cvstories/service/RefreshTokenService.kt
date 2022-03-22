package sk.bumaza.cvstories.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import sk.bumaza.cvstories.entity.managment.RefreshToken
import sk.bumaza.cvstories.repository.AccountRepository
import sk.bumaza.cvstories.repository.RefreshTokenRepository
import sk.bumaza.cvstories.security.jwt.TokenRefreshException
import java.time.Instant
import java.util.*
import javax.transaction.Transactional

@Service
class RefreshTokenService {

    @Value("\${resmalizer.app.jwtRefreshExpirationMs}")
    private val refreshTokenDurationMs = 0L

    @Autowired
    lateinit var refreshTokenRepository: RefreshTokenRepository

    @Autowired
    lateinit var accountRepository: AccountRepository

    fun createRefreshToken(userId: Long?): RefreshToken? {
        val refreshToken = RefreshToken(
            account = accountRepository.findById(userId!!).get(),
            token = UUID.randomUUID().toString(),
            expiryDate = Instant.now().plusMillis(refreshTokenDurationMs))
        return refreshTokenRepository.save(refreshToken)
    }

    fun verifyExpiration(token: RefreshToken): RefreshToken? {
        if (token.expiryDate!!.compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token)
            throw TokenRefreshException(token.token ?: "", "Refresh token was expired. Please make a new signin request")
        }

        return token
    }

    @Transactional
    fun deleteByUserId(userId: Long): Int {
        return refreshTokenRepository.deleteByAccount(accountRepository.findById(userId).get())
    }

}