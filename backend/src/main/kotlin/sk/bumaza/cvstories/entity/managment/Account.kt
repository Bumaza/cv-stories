package sk.bumaza.cvstories.entity.managment

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sk.bumaza.cvstories.constant.AccountType
import sk.bumaza.cvstories.entity.base.BaseEntity
import sk.bumaza.cvstories.entity.data.Feedback
import sk.bumaza.cvstories.entity.data.Resume
import sk.bumaza.cvstories.entity.data.Story
import sk.bumaza.cvstories.security.services.User
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
data class Account(

    val email: String? = null,
    val username: String? = null,

    @JsonIgnore
    val password: String? = null,
    val type: AccountType = AccountType.FREE,

    @OneToOne
    var refreshToken: RefreshToken? = null,

    @OneToOne
    var resume: Resume? = null,

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    var stories: MutableSet<Story> = mutableSetOf(),

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    var feedbacks: MutableSet<Feedback> = mutableSetOf()

    ) : BaseEntity() {

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Account

        return Objects.equals(id, (other).id)
    }

}

fun Account.toTestUser() = User(account = this, username = "", email = "", password = "", authorities = mutableSetOf(), id=id)