package sk.bumaza.cvstories.entity.data

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sk.bumaza.cvstories.entity.base.BaseEntity
import sk.bumaza.cvstories.entity.managment.Account
import java.util.*
import javax.persistence.*

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "link")
data class Link (
    val url: String? = null,
    var description: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    val account: Account? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="personalInfo_id")
    var personalInfo: PersonalInfo? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="story_id")
    var story: Story? = null

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