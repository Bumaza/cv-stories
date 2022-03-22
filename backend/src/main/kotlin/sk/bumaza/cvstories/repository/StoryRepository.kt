package sk.bumaza.cvstories.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import sk.bumaza.cvstories.constant.AccessType
import sk.bumaza.cvstories.entity.data.Story

interface StoryRepository : PagingAndSortingRepository<Story, Long> {

    //select NOT VIEWed and NOT MY and NOT PRIVATE
    fun findByResumeAccessTypeAndFeedbacksAccountIdIsNotAndAccountIdNot(accessType: AccessType, accountId: Long, ownerId: Long, pageable: Pageable) : Page<Story>
}