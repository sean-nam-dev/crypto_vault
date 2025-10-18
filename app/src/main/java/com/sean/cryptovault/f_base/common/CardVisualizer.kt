package com.sean.cryptovault.f_base.common

object CardVisualizer {
    fun number(text: String): String = text.chunked(4).joinToString(" ")
    fun validThru(text: String): String = text.chunked(2).joinToString("/")
}