package com.example.moviesdirector.domain.exceptions

class InternetExceptions : Exception() {
    override val message: String
        get() = "Check your internet, please"
}

