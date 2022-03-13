package sk.bumaza.cvstories.entity.managment

import lombok.AllArgsConstructor
import java.time.Instant
import javax.persistence.*


@Entity
@AllArgsConstructor
@Table(name="resettokens")
data class ResetToken(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @OneToOne(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    var account: Account? = null,

    @Column(nullable=false, unique = true)
    var token: String? = null,

    @Column(nullable = false)
    var expiryDate: Instant? = null
)