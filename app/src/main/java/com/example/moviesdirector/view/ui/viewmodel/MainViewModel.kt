package com.example.moviesdirector.view.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdirector.view.ui.models.ModelResultDetails
import com.example.moviesdirector.view.ui.models.Result
import com.example.moviesdirector.view.ui.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _listMovies = MutableLiveData<List<Result>>()
    val listMovies: LiveData<List<Result>> = _listMovies

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun getListMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val requestAPI = repository.getListMovies()
            requestAPI.enqueue(object : Callback<ModelResultDetails?> {
                override fun onResponse(
                    call: Call<ModelResultDetails?>,
                    response: Response<ModelResultDetails?>
                ) {
                    val resultado = response.body()
                    resultado.let { _listMovies.postValue(resultado?.results) }
                }

                override fun onFailure(call: Call<ModelResultDetails?>, t: Throwable) {
                    _errorMsg.postValue(t.message)
                }
            })
        }
    }
}
