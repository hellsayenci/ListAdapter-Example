package com.example.settingsexample

open class BaseSessionData(open val type: Int)

data class SessionHeader(override val type: Int, val text: String) : BaseSessionData(type)
data class Session(override val type: Int, val text: String) : BaseSessionData(type)
data class SessionFooter(override val type: Int, val text: String) : BaseSessionData(type)