package com.example.moviesdirector.view.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdirector.view.ui.models.ModelListMovies
import com.example.moviesdirector.view.ui.models.ModelMovies
import com.example.moviesdirector.view.ui.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _listMovies = MutableLiveData<List<ModelListMovies>>()
    val listMovies: LiveData<List<ModelListMovies>> = _listMovies

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun getListMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val requestAPI = repository.getListMovies()
            requestAPI.enqueue(object : Callback<ModelMovies?> {
                override fun onResponse(
                    call: Call<ModelMovies?>,
                    response: Response<ModelMovies?>
                ) {
                    val responseMovies = response.body()
                    responseMovies.let { _listMovies.postValue(responseMovies?.results) }
                }

                override fun onFailure(call: Call<ModelMovies?>, t: Throwable) {
                    _errorMsg.postValue(t.message)
                }
            })
        }
    }
}
