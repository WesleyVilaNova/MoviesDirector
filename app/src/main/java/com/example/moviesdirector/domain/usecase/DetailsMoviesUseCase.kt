package com.example.moviesdirector.domain.usecase

import com.example.moviesdirector.data.repository.ListMoviesRepository
import com.example.moviesdirector.domain.models.ModelDetailsMovies
import retrofit2.await

class DetailsMoviesUseCase(private val listMoviesRepository: ListMoviesRepository) {

    suspend fun getResultDetailsMovies(id: Int): ModelDetailsMovies {
        return listMoviesRepository.getDetailsMovies(id).await()
    }
}
