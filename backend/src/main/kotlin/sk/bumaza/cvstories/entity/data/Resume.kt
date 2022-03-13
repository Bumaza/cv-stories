package sk.bumaza.cvstories.entity.data

import sk.bumaza.cvstories.constant.AccessType
import sk.bumaza.cvstories.entity.base.BaseEntity
import sk.bumaza.cvstories.entity.managment.Account
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "resumes")
data class Resume(

    var slug: String? = null,

    @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER)
    var stories: MutableSet<Story>? = mutableSetOf(),

    @OneToOne(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "personalInfo_id", referencedColumnName = "id")
    var personalInfo: PersonalInfo? = null,

    var accessType: AccessType? = null,

    @OneToOne(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    var account: Account? = null,


    ) : BaseEntity(){

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Resume

        return Objects.equals(id, (other).id)
    }

}