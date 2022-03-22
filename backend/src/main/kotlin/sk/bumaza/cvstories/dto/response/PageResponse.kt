package sk.bumaza.cvstories.dto.response

import org.springframework.data.domain.Page
import sk.bumaza.cvstories.entity.data.Story
import java.util.ArrayList

data class PageResponse<T>(
    val data: List<T> = ArrayList(),
    val currentPage: Int = 0,
    val totalItems: Long = 0,
    val totalPages: Int = 0,
)

fun<T> Page<T>.toPageResponse() = PageResponse(content, number, totalElements, totalPages)

fun Page<Story>.toDAOPageResponse() = PageResponse(content.map { it }, number, totalElements, totalPages)
