package sk.bumaza.cvstories.entity.data

import javax.persistence.*

@Entity
@Table(name = "locations")
data class Location(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    var address: String? = null,
    var city: String? = null,
    var country: String? = null,

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    var stories: MutableSet<Story> = hashSetOf(),

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    var personalData: MutableSet<PersonalInfo> = hashSetOf()
)
