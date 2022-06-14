package com.example.moviesdirector.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdirector.domain.exceptions.ErrorHandling
import com.example.moviesdirector.domain.exceptions.InternetExceptions
import com.example.moviesdirector.domain.exceptions.InvalidKeyExceptions
import com.example.moviesdirector.domain.exceptions.NotBeFoundExceptions
import com.example.moviesdirector.domain.models.ModelDetailsMovies
import com.example.moviesdirector.domain.usecase.DetailsMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailsMoviesViewModel(private val detailsMoviesUseCase: DetailsMoviesUseCase) : ViewModel() {

    private val _viewDetailsListMovies = MutableLiveData<ModelDetailsMovies?>()
    val viewDetailsListMovies: MutableLiveData<ModelDetailsMovies?> = _viewDetailsListMovies

    private val _viewError = MutableLiveData<ErrorHandling<String>>()
    val viewError: LiveData<ErrorHandling<String>> = _viewError

    fun getDetailsMovies(id: Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val responseMoviesDetails = detailsMoviesUseCase.getResultDetailsMovies(id)
                viewDetailsListMovies.postValue(responseMoviesDetails)
            }
        } catch (http: HttpException) {
            when {
                http.code() == 7 -> {
                    _viewError.value = ErrorHandling.Error(InvalidKeyExceptions(), http.code())
                }
                http.code() == 34 -> {
                    _viewError.value = ErrorHandling.Error(NotBeFoundExceptions(), http.code())
                }
                http.code() != 7 -> {
                    _viewError.value = ErrorHandling.Error(InternetExceptions(), http.code())
                }
            }
        }
    }

    class DetailsViewModelFactory(private val detailsMoviesUseCase: DetailsMoviesUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DetailsMoviesViewModel::class.java)) {
                DetailsMoviesViewModel(this.detailsMoviesUseCase) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
