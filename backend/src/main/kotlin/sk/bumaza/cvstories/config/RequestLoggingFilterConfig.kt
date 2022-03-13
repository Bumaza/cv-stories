package sk.bumaza.cvstories.config

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Component
class RequestLoggingFilterConfig {

    @Bean
    fun logFilter() : CommonsRequestLoggingFilter {
        val filter = CommonsRequestLoggingFilter()
        filter.setIncludeQueryString(true)
        filter.setIncludePayload(true)
        filter.setMaxPayloadLength(10000)
        filter.setIncludeHeaders(true)
        return filter
    }

}