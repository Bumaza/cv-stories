package sk.bumaza.cvstories.security.services

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import sk.bumaza.cvstories.entity.managment.Account
import java.util.*

class User(
    val id: Long?, private val username: String, val email: String?,
    @field:JsonIgnore private val password: String, val account: Account?,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null) return false
        val user = o as User
        return id == user.id
    }

    companion object {
        private const val serialVersionUID = 1L

        fun build(account: Account): User {
//            val authorities = account.roles.stream()
//                    .map { role: Role -> SimpleGrantedAuthority(role.name!!.name) }
//                    .collect(Collectors.toList())
            return User(
                account.id, account.username!!,
                account.email, account.password!!,
                account, Collections.emptyList())
        }
    }
}