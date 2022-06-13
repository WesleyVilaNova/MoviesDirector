package com.example.moviesdirector.view.ui.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdirector.view.ui.domain.models.ModelListMovies
import com.example.moviesdirector.view.ui.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.await

class ListMoviesViewModel(private val repository: MainRepository) : ViewModel() {

    private val _listMovies = MutableLiveData<List<ModelListMovies>>()
    val listMovies: LiveData<List<ModelListMovies>> = _listMovies

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun getListMovies() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val responseListMovies = repository.getListMovies().await()
                _listMovies.postValue(responseListMovies?.let { responseListMovies.results })
            }
        } catch (http: HttpException) {
            _errorMsg.value = http.code().toString()
        }
    }

    class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(ListMoviesViewModel::class.java)) {
                ListMoviesViewModel(this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel not found")
            }
        }
    }
}
