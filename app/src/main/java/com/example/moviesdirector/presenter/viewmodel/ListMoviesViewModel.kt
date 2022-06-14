package com.example.moviesdirector.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdirector.domain.exceptions.ErrorHandling
import com.example.moviesdirector.domain.exceptions.InternetExceptions
import com.example.moviesdirector.domain.exceptions.InvalidKeyExceptions
import com.example.moviesdirector.domain.exceptions.NotBeFoundExceptions
import com.example.moviesdirector.domain.models.ModelListMovies
import com.example.moviesdirector.domain.usecase.ListMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ListMoviesViewModel(private val listMoviesUseCase: ListMoviesUseCase) : ViewModel() {

    private val _listMovies = MutableLiveData<ErrorHandling<List<ModelListMovies>>>()
    val listMovies: LiveData<ErrorHandling<List<ModelListMovies>>> = _listMovies

    private val _errorMsg = MutableLiveData<ErrorHandling<String>>()
    val errorMsg: LiveData<ErrorHandling<String>> = _errorMsg

    fun getListMovies() {
        try {
            _listMovies.value = ErrorHandling.Loading()
            CoroutineScope(Dispatchers.IO).launch {
                val responseListMovies = listMoviesUseCase.getResultListMovies()
                _listMovies.postValue(ErrorHandling.Success(responseListMovies))
            }
        } catch (http: HttpException) {
            when {
                http.code() == 7 -> {
                    _errorMsg.value = ErrorHandling.Error(InvalidKeyExceptions(), http.code())
                }
                http.code() == 34 -> {
                    _errorMsg.value = ErrorHandling.Error(NotBeFoundExceptions(), http.code())
                }
                http.code() != 7 -> {
                    _errorMsg.value = ErrorHandling.Error(InternetExceptions(), http.code())
                }
            }
        }
    }

    class MainViewModelFactory(private val listMoviesUseCase: ListMoviesUseCase) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(ListMoviesViewModel::class.java)) {
                ListMoviesViewModel(this.listMoviesUseCase) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
