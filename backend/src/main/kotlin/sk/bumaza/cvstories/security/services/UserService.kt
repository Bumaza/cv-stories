package sk.bumaza.cvstories.security.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sk.bumaza.cvstories.entity.managment.Account
import sk.bumaza.cvstories.repository.AccountRepository

@Service
class UserService(
    val accountRepository: AccountRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(userName: String): UserDetails {
        val account: Account = accountRepository.findByUsername(userName)
            ?.orElseThrow { UsernameNotFoundException("Account Not Found with username: " +
                    "$userName") }!!
        return User.build(account)
    }
}