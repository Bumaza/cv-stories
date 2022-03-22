package sk.bumaza.cvstories.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import sk.bumaza.cvstories.dto.request.ResumeRequest
import sk.bumaza.cvstories.security.services.User
import sk.bumaza.cvstories.service.ResumeService
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/resume")
class ResumeController(val resumeService: ResumeService) {

    @GetMapping("/{slug}")
    fun getResume(@PathVariable(value = "slug") slug: String,
        @AuthenticationPrincipal user: User) = resumeService.getResume(user, slug)

    @PostMapping
    fun postResume(@Valid @RequestBody resumeRequest: ResumeRequest,
        @AuthenticationPrincipal user: User) = resumeService.createResume(user, resumeRequest)

    @PutMapping("/{slug}")
    fun putResume(@PathVariable(value = "slug") slug: String,
                  @Valid @RequestBody resumeRequest: ResumeRequest,
                  @AuthenticationPrincipal user: User) = resumeService.updateResume(user, slug, resumeRequest)

    @DeleteMapping("/{slug}")
    fun deleteResume(@PathVariable(value = "slug") slug: String,
                  @AuthenticationPrincipal user: User) = resumeService.deleteResume(user, slug)

}