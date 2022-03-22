package sk.bumaza.cvstories.extensions

import org.springframework.data.domain.Page
import sk.bumaza.cvstories.dto.response.Error
import sk.bumaza.cvstories.dto.response.PageResponse
import sk.bumaza.cvstories.dto.response.ResponseWrapper

fun<T> Page<T>.toPageResponse() = PageResponse(content, number, totalElements, totalPages)

fun Error.toResponse() = ResponseWrapper(this, success = false)
