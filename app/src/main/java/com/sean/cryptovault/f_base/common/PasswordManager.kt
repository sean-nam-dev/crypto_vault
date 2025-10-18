package com.sean.cryptovault.f_base.common

object PasswordManager {
    fun checkProblems(password: String, problemList: List<String>): List<String> {
        val currentProblemList = mutableListOf<String>()

        val lowercaseAlphabet = 'a'..'z'
        val uppercaseAlphabet = 'A'..'Z'
        val specialSigns = listOf(
            '!', '@', '#',
            '$', '%', '^',
            '&', '*', '(',
            ')', '_', '+',
            '-', '=', '{',
            '}', '[', ']',
            '|', '\\', ';',
            ':', '"', '\'',
            '<', '>', ',',
            '.', '/', '?'
        )

        var digitsCounter = 0
        var lowercaseCounter = 0
        var uppercaseCounter = 0
        var specialSignsCounter = 0

        password.forEach {
            if (it.isDigit()) {
                digitsCounter++
            } else if (it in lowercaseAlphabet) {
                lowercaseCounter++
            } else if (it in uppercaseAlphabet) {
                uppercaseCounter++
            } else if (it in specialSigns) {
                specialSignsCounter++
            }
        }

        if (password.length < 12) currentProblemList += problemList[0]
        if (digitsCounter < 2) currentProblemList += problemList[1]
        if (lowercaseCounter < 2) currentProblemList += problemList[2]
        if (uppercaseCounter < 2) currentProblemList += problemList[3]
        if (specialSignsCounter < 2) currentProblemList += problemList[4]

        return currentProblemList
    }
}