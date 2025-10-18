package com.sean.cryptovault.f_base.common

object PasswordGenerator {
    private val alphabet = ('a'..'z').toList()
    private val uppercaseAlphabet = ('A'..'Z')
    private val numbers = ('0'..'9').toList()
    private val specialSigns = listOf(
        '(', ')', '*', '-', '+',
        '%', '$', '.', ',', '<',
        '>', '?', '!', '_', '@',
        '^', '/', '&', '#', ':'
    )

    fun getPassword(
        length: Int,
        isDigitIncluded: Boolean,
        isSpecialSignIncluded: Boolean,
        isUppercaseLetterIncluded: Boolean
    ): String {
        var password = ""

        repeat(2) {
            password += alphabet.random()
            if (isDigitIncluded) password += numbers.random()
            if (isSpecialSignIncluded) password += specialSigns.random()
            if (isUppercaseLetterIncluded) password += uppercaseAlphabet.random()
        }

        password += (
                alphabet +
                if (isDigitIncluded) numbers else emptyList<List<Char>>() +
                if (isSpecialSignIncluded) specialSigns else emptyList<List<Char>>() +
                if (isUppercaseLetterIncluded) uppercaseAlphabet else emptyList<List<Char>>()
            ).shuffled().take(length - password.length).joinToString("")

        return password.toList().shuffled().joinToString("")
    }
}