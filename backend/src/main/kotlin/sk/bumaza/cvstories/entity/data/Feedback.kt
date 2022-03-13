package sk.bumaza.cvstories.entity.data

import org.hibernate.annotations.CreationTimestamp
import sk.bumaza.cvstories.constant.FeedbackType
import sk.bumaza.cvstories.entity.managment.Account
import java.time.Instant
import javax.persistence.*


@Entity
@Table(name = "feedbacks")
data class Feedback(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="story_id")
    var story: Story? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    var account: Account? = null,

    var comment: String? = null,
    var type: FeedbackType? = null,

    @CreationTimestamp
    var createDate: Instant? = null
)