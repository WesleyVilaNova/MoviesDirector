package com.example.moviesdirector.domain.exceptions

import com.example.moviesdirector.domain.models.ModelListMovies
import java.lang.Exception

sealed class ErrorHandling<out T : Any?>() {

    class Loading : ErrorHandling<Nothing>()
    class Success<out T : Any?>(val listMovies: List<ModelListMovies>) : ErrorHandling<T>()
    class Error(val exception: Exception, val codeHttp: Int = 0) : ErrorHandling<Nothing>()
}
