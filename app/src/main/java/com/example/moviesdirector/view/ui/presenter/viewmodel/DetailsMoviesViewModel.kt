package com.example.moviesdirector.view.ui.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdirector.view.ui.domain.models.ModelDetailsMovies
import com.example.moviesdirector.view.ui.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.await

class DetailsMoviesViewModel(private val repository: MainRepository) : ViewModel() {

    private val _viewDetailsListMovies = MutableLiveData<ModelDetailsMovies?>()
    val viewDetailsListMovies: MutableLiveData<ModelDetailsMovies?> = _viewDetailsListMovies

    private val _viewError = MutableLiveData<String>()
    val viewError: LiveData<String> = _viewError

    fun getDetailsMovies(id: Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val responseMoviesDetails = repository.getListMoviesDetails(id).await()
                viewDetailsListMovies.postValue(responseMoviesDetails)
            }
        } catch (e: HttpException) {
            _viewError.value = e.code().toString()
        }
    }

    class DetailsViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(DetailsMoviesViewModel::class.java)) {
                DetailsMoviesViewModel(this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
