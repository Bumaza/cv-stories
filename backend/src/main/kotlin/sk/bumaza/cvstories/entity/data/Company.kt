package sk.bumaza.cvstories.entity.data

import javax.persistence.*

@Entity
@Table(name = "companies")
data class Company(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    var name: String? = null,
    var logoUrl: String? = null,

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    var stories: MutableSet<Story> = hashSetOf(),
)