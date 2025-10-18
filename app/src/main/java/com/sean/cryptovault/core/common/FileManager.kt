package com.sean.cryptovault.core.common

import java.io.File

class FileManager(
    filesDir: File,
    fileName: String,
    defaultValue: String
) {
    val file = File(filesDir, fileName)

    fun assign(value: String) {
        file.writeText(value)
    }

    fun read(): String = file.readText()

    init {
        file.run {
            if (!exists()) {
                createNewFile()
                writeText(defaultValue)
            }
        }
    }
}