package sk.bumaza.cvstories.repository

import org.springframework.data.repository.CrudRepository
import sk.bumaza.cvstories.entity.data.Resume
import java.util.*

interface ResumeRepository : CrudRepository<Resume, Long> {

    fun findBySlug(slug: String?) : Optional<Resume>
    fun existsBySlug(slug: String?) : Boolean?
}