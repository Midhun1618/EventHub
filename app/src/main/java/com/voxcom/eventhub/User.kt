package com.voxcom.eventhub.models

class User (
    val u: String,
    val e: String,
    val p :String,
    var isLoggedIn: Boolean = false,
    var selectedEvents: MutableList<String> = mutableListOf(),
    var tasks: MutableList<String> = mutableListOf())