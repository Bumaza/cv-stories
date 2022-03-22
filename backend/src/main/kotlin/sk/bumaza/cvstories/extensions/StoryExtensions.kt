package sk.bumaza.cvstories.extensions


import sk.bumaza.cvstories.repository.StoryRepository
import sk.bumaza.cvstories.security.services.User
import java.time.Instant


fun StoryRepository.notExist(storyId: Long) = existsById(storyId).not()

fun StoryRepository.softDelete(storyId: Long) {
    val story = findById(storyId).get()
    story.deletedAt = Instant.now()
    save(story)
}

fun StoryRepository.isOwner(storyId: Long, user: User) : Boolean {
    val story = findById(storyId).get()
    return story.account != null && story.account?.id == user.id
}

