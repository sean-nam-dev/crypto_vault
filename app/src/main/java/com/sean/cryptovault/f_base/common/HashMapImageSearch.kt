package com.sean.cryptovault.f_base.common

fun imageHashMapSearch(searchText: String, hashMap: HashMap<String, Int>): HashMap<String, Int> {
    val newHashMap: HashMap<String, Int> = hashMapOf()
    val currentSearchValue = searchText.trim().lowercase()

    return if (currentSearchValue.isBlank()) {
        hashMap
    } else {
        hashMap.forEach { (key, value) ->
            val maxLength = if (key.length <= currentSearchValue.length) key.length
            else currentSearchValue.length

            if (key.take(maxLength) == currentSearchValue.take(maxLength)) {
                newHashMap[key] = value
            }
        }
        newHashMap
    }
}