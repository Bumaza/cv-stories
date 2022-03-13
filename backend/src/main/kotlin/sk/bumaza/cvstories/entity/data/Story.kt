package sk.bumaza.cvstories.entity.data

import sk.bumaza.cvstories.constant.ContractType
import sk.bumaza.cvstories.constant.CurrencyType
import sk.bumaza.cvstories.constant.StoryType
import sk.bumaza.cvstories.entity.base.BaseEntity
import sk.bumaza.cvstories.entity.managment.Account
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "stories")
data class Story(

    //header
    var type: StoryType? = null,
    var contractType: ContractType? = null,

    var positionName: String? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="company_id")
    var company: Company? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="resume_id")
    var resume: Resume? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="location_id")
    var location: Location? = null,

    var startDate: Date? = null,
    var endDate: Date? = null, //if null present

    //data
    var text: String? = null,

    var salary: Double? = null,
    var currency: CurrencyType? = null,

    @OneToMany(mappedBy = "story", fetch = FetchType.EAGER)
    var links: MutableList<Link>? = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    var account: Account? = null,

    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY)
    var feedbacks: MutableList<Feedback>? = mutableListOf()

) : BaseEntity() {

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Story
        return Objects.equals(id, (other).id)
    }
}