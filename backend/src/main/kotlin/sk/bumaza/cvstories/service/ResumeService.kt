package sk.bumaza.cvstories.service

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import sk.bumaza.cvstories.constant.AccessType
import sk.bumaza.cvstories.dto.request.ResumeRequest
import sk.bumaza.cvstories.dto.request.toDAO
import sk.bumaza.cvstories.dto.request.toEntity
import sk.bumaza.cvstories.dto.response.Error
import sk.bumaza.cvstories.dto.response.Error.Companion.notFound
import sk.bumaza.cvstories.dto.response.ResponseWrapper
import sk.bumaza.cvstories.extensions.isOwner
import sk.bumaza.cvstories.extensions.notExist
import sk.bumaza.cvstories.extensions.softDelete
import sk.bumaza.cvstories.extensions.toResponse
import sk.bumaza.cvstories.repository.ResumeRepository
import sk.bumaza.cvstories.security.services.User


@Service
class ResumeService(
    val resumeRepository: ResumeRepository
) {


    fun getResume(user: User, slug: String) : ResponseEntity<*> {
        if(resumeRepository.notExist(slug)){
            return ResponseEntity(Error.notFound.toResponse(), HttpStatus.BAD_REQUEST)
        }

        val resume = resumeRepository.findBySlug(slug = slug).get()
        if(resume.accessType == AccessType.PUBLIC){
            return ResponseEntity.ok(ResponseWrapper(data=resume.toDAO()))
        }

        return ResponseEntity(Error.notFound.toResponse(), HttpStatus.BAD_REQUEST)
    }

    fun createResume(user: User, request: ResumeRequest) : ResponseEntity<*> {
        if(resumeRepository.existsBySlug(request.slug) == true){
            return ResponseEntity(notFound.toResponse(), HttpStatus.BAD_REQUEST)
        }

        val resume = request.toEntity(user.account)
        val savedObject = resumeRepository.save(resume)

        return ResponseEntity(ResponseWrapper(data=savedObject.toDAO()), HttpStatus.CREATED)
    }

    fun updateResume(user: User, slug: String, request: ResumeRequest) : ResponseEntity<*> {
        if(resumeRepository.notExist(slug)){
            return ResponseEntity(Error.notFound.toResponse(), HttpStatus.BAD_REQUEST)
        }

        if(resumeRepository.isOwner(slug, user).not()){
            return ResponseEntity(Error.notAuthorized.toResponse(), HttpStatus.BAD_REQUEST)
        }

        //TODO save update
        val currResume = resumeRepository.findBySlug(slug).get()
        val reqEntity = request.toEntity(user.account)
       // val resume = currResume.copy(slug= reqEntity.slug, stories = reqEntity.stories, reqEntity.)


        return ResponseEntity.ok().body(ResponseWrapper(data=null))
    }

    fun deleteResume(user: User, slug: String) : ResponseEntity<*>{
        if(resumeRepository.notExist(slug)){
            return ResponseEntity(Error.notFound.toResponse(), HttpStatus.BAD_REQUEST)
        }

        if(resumeRepository.isOwner(slug, user).not()){
            return ResponseEntity(Error.notAuthorized.toResponse(), HttpStatus.BAD_REQUEST)
        }

        resumeRepository.softDelete(slug)

        return ResponseEntity(ResponseWrapper(data=null), HttpStatus.ACCEPTED)
    }

}