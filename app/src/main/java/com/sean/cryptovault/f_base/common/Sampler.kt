package com.sean.cryptovault.f_base.common

//object Sampler {
//    val lowercaseAlphabet = 'a'..'z'
//    val uppercaseAlphabet = 'A'..'Z'
//    val extensions = listOf("@gmail.com", "@list.ru", "@mail.ru")
//    val length = 8..16
//
//    fun getVault(): Vault {
//        return Vault(
//            imageId = listOf(R.drawable.logo_test_1, R.drawable.logo_test_2, R.drawable.logo_test_3).random(),
//            service = "${uppercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}",
//            identifier = "${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${extensions.random()}",
//            password = "${uppercaseAlphabet.random()}${lowercaseAlphabet.random()}${length.random()}",
//            link = "",
//            isFavorite = listOf(true, false, false, false, false).random()
//        )
//    }
//    fun getCard(): Card {
//        return Card(
//            imageId = R.drawable.ic_visa,
//            number = "${(1000000000000000..9999999999999999).random()}",
//            validThru = "${(1000..9999).random()}",
//            cvv = "${(100..999).random()}",
//            fullName = "${uppercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}${lowercaseAlphabet.random()}",
//            isFavorite = listOf(true, false, false, false, false).random()
//        )
//    }
//}