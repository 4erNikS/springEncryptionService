package configuration

import controller.AppController
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import service.EncryptionService

@EnableAutoConfiguration
@Configuration
class AppConfig {
    @Bean
    fun encryptionService() = EncryptionService("gg765716253gfhg")

    @Bean
    fun testController() = AppController(encryptionService())
}