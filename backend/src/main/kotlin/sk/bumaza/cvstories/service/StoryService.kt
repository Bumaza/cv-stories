package sk.bumaza.cvstories.service

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sk.bumaza.cvstories.constant.AccessType
import sk.bumaza.cvstories.dto.response.ResponseWrapper
import sk.bumaza.cvstories.dto.response.toDAOPageResponse
import sk.bumaza.cvstories.repository.StoryRepository
import sk.bumaza.cvstories.security.services.User

@Service
class StoryService(
    private val storyRepository: StoryRepository
) {

    fun getStories(user: User, pageNumber: Int, pageSize: Int) : ResponseEntity<*> {

        val data = storyRepository
            .findByResumeAccessTypeAndFeedbacksAccountIdIsNotAndAccountIdNot(
                AccessType.PUBLIC, user.id ?: 0L, user.id ?: 0L,
                PageRequest.of(pageNumber, pageSize))
            .toDAOPageResponse()

        return ResponseEntity.ok(ResponseWrapper(data))
    }

}