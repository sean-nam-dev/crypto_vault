package com.sean.cryptovault.f_base.common.cryptography

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.sean.cryptovault.f_base.common.PasswordGenerator
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CryptoManager {
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encryptCipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry("secret", null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "secret",
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    private fun encrypt(
        file: File,
        bytes: ByteArray
    ): ByteArray {
        val encryptedBytes = encryptCipher.doFinal(bytes)

        if (file.readText().isEmpty()) {
            FileOutputStream(file).use {
                it.write(encryptCipher.iv.size)
                it.write(encryptCipher.iv)
                it.write(encryptedBytes.size)
                it.write(encryptedBytes)
            }
        }

        return encryptedBytes
    }

    private fun decrypt(inputStream: InputStream): ByteArray {
        return inputStream.use {
            val ivSize = it.read()
            val iv = ByteArray(ivSize)
            it.read(iv)

            val encryptedBytesSize = it.read()
            val encryptedBytes = ByteArray(encryptedBytesSize)
            it.read(encryptedBytes)

            getDecryptCipherForIv(iv).doFinal(encryptedBytes)
        }
    }

    fun getFactory(filesDir: File): SupportOpenHelperFactory {
        System.loadLibrary("sqlcipher")

        val file = File(filesDir, ENCRYPTION_KEY).apply {
            if (!this.exists()) {
                createNewFile()

                encrypt(
                    file = this,
                    bytes = PasswordGenerator.getPassword(
                        length = 16,
                        isDigitIncluded = true,
                        isSpecialSignIncluded = true,
                        isUppercaseLetterIncluded = true
                    ).encodeToByteArray()
                )
            }
        }

        return SupportOpenHelperFactory(decrypt(FileInputStream(file)))
    }

    companion object {
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
        private const val ENCRYPTION_KEY = "encryption_key"
    }
}