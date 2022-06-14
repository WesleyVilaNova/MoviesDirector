package com.example.moviesdirector.domain.exceptions

class InvalidKeyExceptions : Exception() {
    override val message: String
        get() = "Invalid API key: You must be granted a valid key"
}

