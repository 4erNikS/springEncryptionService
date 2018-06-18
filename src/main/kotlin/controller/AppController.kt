package controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.time.Instant
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.EncryptionService

@Component
@RestController
class AppController {

    private val encryptionService: EncryptionService

    @Autowired
    constructor(@Qualifier("auditRoleLogic")  encryptionService:EncryptionService) {
        this.encryptionService = encryptionService
    }

    @RequestMapping("/")
    fun index():String {
        //val encryptionService = EncryptionService("gg765716253gfhg")
        return encryptionService.encrypt("666")
    }
}
