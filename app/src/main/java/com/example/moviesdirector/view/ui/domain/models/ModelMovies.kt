package com.example.moviesdirector.view.ui.domain.models

import java.io.Serializable

data class ModelMovies(
    val results: List<ModelListMovies>
) : Serializable
