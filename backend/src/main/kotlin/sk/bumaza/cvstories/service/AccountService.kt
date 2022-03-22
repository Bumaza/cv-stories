package sk.bumaza.cvstories.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import sk.bumaza.cvstories.constant.RESET_TOKEN_LIFETIME_IN_DAYS
import sk.bumaza.cvstories.dto.request.ForgotPasswordRequest
import sk.bumaza.cvstories.dto.request.ResetPasswordRequest
import sk.bumaza.cvstories.dto.request.UpdateAccountRequest
import sk.bumaza.cvstories.repository.AccountRepository
import sk.bumaza.cvstories.repository.ResetTokenRepository
import sk.bumaza.cvstories.security.services.User
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import sk.bumaza.cvstories.dto.response.Error
import sk.bumaza.cvstories.dto.response.ResponseWrapper
import sk.bumaza.cvstories.entity.managment.Account
import sk.bumaza.cvstories.entity.managment.ResetToken
import sk.bumaza.cvstories.extensions.isNotOwner
import sk.bumaza.cvstories.extensions.toResponse
import sk.bumaza.cvstories.util.StrengthPasswordTypes
import sk.bumaza.cvstories.util.strengthChecker

@Service
class AccountService(
    val accountRepository: AccountRepository,
    val passwordEncoder: PasswordEncoder,
    val resetTokenRepository: ResetTokenRepository,
    val emailService : EmailService
)  {

    @Value("\${resmalizer.app.baseUrl}")
    private val baseUrl: String = ""

    fun getAccountDetail(accountId: Long, user: User) : ResponseEntity<*> {
        if(user.isNotOwner(accountId)) {
            return ResponseEntity.badRequest().body(Error.notAuthorized.toResponse())
        }

        val accountData = accountRepository.findById(accountId)

        return ResponseEntity.ok(ResponseWrapper(data=accountData))
    }


    fun deleteAccount(accountId: Long, user: User) : ResponseEntity<*> {
        if(user.isNotOwner(accountId)) {
            return ResponseEntity.badRequest().body(Error.notAuthorized.toResponse())
        }

        if(accountRepository.existsById(accountId).not()){
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        accountRepository.deleteById(accountId)
        return ResponseEntity(ResponseWrapper(success = true, data = null), HttpStatus.ACCEPTED)
    }

    fun updateAccount(accountId: Long, user: User, newAccount: UpdateAccountRequest) : ResponseEntity<*> {
        if(user.isNotOwner(accountId)) {
            return ResponseEntity.badRequest().body(Error.notAuthorized.toResponse())
        }

        val account : Optional<Account> = accountRepository.findById(accountId)

        if(account.isPresent.not()){
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        //TODO change name
        val updateAccount = account.get().copy()
        return ResponseEntity.ok().body(accountRepository.save(updateAccount))
    }

    fun resetPassword(request: ResetPasswordRequest?) : ResponseEntity<*> {
        if(request == null){
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        val account = accountRepository.findByEmail(request.email)

        if(account.isPresent.not()){ //not in db
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        val isTokenValidation = request.token.isNullOrEmpty().not()

        if(isTokenValidation){
            val dbToken = resetTokenRepository.findByToken(request.token)
            if(dbToken.isPresent.not()){
                return ResponseEntity.badRequest().body(Error.notFound.toResponse())
            }

            if(dbToken.get().expiryDate!!.isBefore(Instant.now())){
                return ResponseEntity.badRequest().body(Error.expirationError.toResponse())
            }
        }else{ //is password validation
            val oldDbPassword = account.get().password ?: ""

            if(passwordEncoder.matches(request.oldPassword, oldDbPassword).not()){
                return ResponseEntity.badRequest().body(Error.loginError.toResponse())
            }

            if(request.password == request.oldPassword){
                return ResponseEntity.badRequest().body(Error.equalsError.toResponse())
            }
        }

        if(request.password?.strengthChecker() == StrengthPasswordTypes.WEAK){
            return ResponseEntity(Error.weakPassword.toResponse(), HttpStatus.NOT_ACCEPTABLE)
        }

        val newEncodedPassword = passwordEncoder.encode(request.password)
        val updateAccount = account.get().copy(password = newEncodedPassword)
        accountRepository.save(updateAccount)

        return ResponseEntity.ok().body(ResponseWrapper(data = "Password changed"))
    }

    fun forgotPassword(request: ForgotPasswordRequest?) : ResponseEntity<*> {
        if(request == null){
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        val account = accountRepository.findByEmail(request.email)

        if(account.isPresent.not()){ //not in db
            return ResponseEntity.badRequest().body(Error.notFound.toResponse())
        }

        val resetToken = UUID.randomUUID().toString()
        val url = "${baseUrl}reset-password?token=$resetToken"

        resetTokenRepository.save(
            ResetToken(account = account.get(),
            token = resetToken,
            expiryDate = Instant.now().plus(RESET_TOKEN_LIFETIME_IN_DAYS, ChronoUnit.DAYS))
        )


        emailService.sendSimpleMessage(request.email, "Resmalizer.io - Password recovery", url)

        return ResponseEntity.ok().body(ResponseWrapper(data = "Email sent"))
    }
}