package service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.stereotype.Service
import java.util.*

@Service("encryptionService")
class EncryptionService {
    private val CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz*&^%$#@!~{}[]+-&:,."

    private val salt = "b1a0ffcd03651af4cde234"
    private val randomPaddingLeft = 5
    private val randomPaddingRight = 17
    private val key:String

    @Autowired
    constructor(key: String) {
        this.key = key
    }

    fun encrypt(secret: String): String {
        val paddedSecret = addRandomPadding(secret, randomPaddingLeft, randomPaddingRight)
        val encryptor = Encryptors.text(key, salt)
        return encryptor.encrypt(paddedSecret)
    }
    fun decrypt(crypted: String): String {
        val decryptor = Encryptors.text(key, salt)
        val paddedSecret = decryptor.decrypt(crypted)
        return removePadding(paddedSecret, randomPaddingLeft, randomPaddingRight)
    }

    private fun addRandomPadding(s: String, left: Int, right: Int): String {
        return getRandomString(left) + s + getRandomString(right)
    }

    private fun removePadding(s: String, left: Int, right: Int): String {
        if(s.isNullOrEmpty() || s.length<left+right) {
            return s
        }
        return s.substring(left, s.length-right)
    }

    private fun getRandomString(length: Int): String {
        val rand = Random()
        val text = CharArray(length)
        for (i in 0..length - 1)
            text[i] = CHARACTERS[rand.nextInt(CHARACTERS.length)]
        return String(text)
    }
}