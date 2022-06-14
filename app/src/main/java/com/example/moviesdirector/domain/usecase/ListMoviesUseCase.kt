package com.example.moviesdirector.domain.usecase

import com.example.moviesdirector.data.repository.ListMoviesRepository
import com.example.moviesdirector.domain.models.ModelListMovies
import retrofit2.await

class ListMoviesUseCase(private val listMoviesRepository: ListMoviesRepository) {

    suspend fun getResultListMovies(): List<ModelListMovies> {
        return listMoviesRepository.getListMovies().let { listMoviesRepository.getListMovies().await().results }
    }
}
