package sk.bumaza.cvstories.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sk.bumaza.cvstories.security.services.User
import sk.bumaza.cvstories.service.StoryService

@RestController
@RequestMapping("/v1/api/stories")
class StoryController(val storyService: StoryService) {

    @GetMapping
    fun getStories(@RequestParam(defaultValue = "0") pageNumber: Int,
                   @RequestParam(defaultValue = "20") pageSize: Int,
                   @AuthenticationPrincipal user: User) = storyService.getStories(user, pageNumber, pageSize)

}