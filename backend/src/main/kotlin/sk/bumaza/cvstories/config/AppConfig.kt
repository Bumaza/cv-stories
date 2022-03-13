package sk.bumaza.cvstories.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*

@Configuration
@ComponentScan(basePackages = ["sk.bumaza.cvstories"])
class AppConfig {

    @Bean
    fun corsFilter() : CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOrigins = Collections.singletonList("*")
        config.allowedHeaders = Collections.singletonList("*")
        config.allowedMethods = Collections.singletonList("*")
        source.registerCorsConfiguration("/**", config)

        return CorsFilter(source)
    }

}