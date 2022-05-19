package com.example.moviesdirector.view.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdirector.view.ui.models.ModelDetailsMovies
import com.example.moviesdirector.view.ui.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(private val repository: MainRepository) : ViewModel() {

    private val _viewDetailsListMovies = MutableLiveData<ModelDetailsMovies?>()
    val viewDetailsListMovies: MutableLiveData<ModelDetailsMovies?> = _viewDetailsListMovies

    private val _viewError = MutableLiveData<String>()
    val viewError: LiveData<String> = _viewError

    fun getDetailsMovies(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getListMoviesDetails(id).enqueue(object : Callback<ModelDetailsMovies?> {
                override fun onResponse(call: Call<ModelDetailsMovies?>, response: Response<ModelDetailsMovies?>) {
                    val responseMoviesDetails = response.body()
                    _viewDetailsListMovies.value = responseMoviesDetails
                }

                override fun onFailure(call: Call<ModelDetailsMovies?>, t: Throwable) {
                }
            })
        }
    }
}
