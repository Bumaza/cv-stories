package sk.bumaza.cvstories.entity.data

import sk.bumaza.cvstories.entity.base.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "personalinfo")
data class PersonalInfo(

    var description: String? = null,
    var skills: String? = null,

    var firstName: String? = null,
    var lastName: String? = null,
    var middleName: String? = null,

    @OneToOne
    var resume: Resume? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="location_id")
    var location: Location? = null,

    @OneToMany(mappedBy = "personalInfo", fetch = FetchType.EAGER)
    var links: MutableSet<Link>? = mutableSetOf()

) : BaseEntity(){

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Link
        return Objects.equals(id, (other).id)
    }
}