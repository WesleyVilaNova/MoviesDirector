package com.example.moviesdirector.domain.exceptions

class NotBeFoundExceptions : Exception() {
    override val message: String
        get() = "The resource you requested could not be found."
}