package org.example.machine

enum class Drinks(
    val displayName: String,
    val price: Int,
) {
    COKE("콜라", 1100),
    WATER("물", 600),
    COFFEE("커피", 700),
}
