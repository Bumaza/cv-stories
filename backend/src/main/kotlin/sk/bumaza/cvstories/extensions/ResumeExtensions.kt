package sk.bumaza.cvstories.extensions

import sk.bumaza.cvstories.repository.ResumeRepository
import sk.bumaza.cvstories.security.services.User
import java.time.Instant


fun ResumeRepository.notExist(slug: String) = (existsBySlug(slug) == true).not()

fun ResumeRepository.softDelete(slug: String) {
    val story = findBySlug(slug).get()
    story.deletedAt = Instant.now()
    save(story)
}

fun ResumeRepository.isOwner(slug: String, user: User) : Boolean {
    val resume = findBySlug(slug).get()
    return resume.account != null && resume.account?.id == user.id
}

fun ResumeRepository.isNotOwner(slug: String, user: User) = isOwner(slug, user).not()