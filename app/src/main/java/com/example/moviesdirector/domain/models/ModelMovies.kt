package com.example.moviesdirector.domain.models

import java.io.Serializable

data class ModelMovies(
    val results: List<ModelListMovies>
) : Serializable
