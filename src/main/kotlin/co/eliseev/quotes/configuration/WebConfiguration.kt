package co.eliseev.quotes.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(CorsConfiguration.ALL)
            .allowedMethods(HttpMethod.HEAD.name)
            .allowedMethods(HttpMethod.DELETE.name)
            .allowedMethods(HttpMethod.GET.name)
            .allowedMethods(HttpMethod.PATCH.name)
            .allowedMethods(HttpMethod.POST.name)
            .allowedMethods(HttpMethod.PUT.name)
            .allowedMethods(HttpMethod.OPTIONS.name)
            .allowedMethods(HttpMethod.TRACE.name)
    }

}
