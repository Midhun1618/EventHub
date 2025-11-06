package com.voxcom.eventhub

data class Event(
    val id: Int,
    val name: String,
    val date: String,
    val location: String,
    val imageResId: Int,
    var isRSVP: Boolean = false
)
