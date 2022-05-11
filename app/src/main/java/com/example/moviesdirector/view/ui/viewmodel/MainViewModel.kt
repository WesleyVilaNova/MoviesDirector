package com.example.moviesdirector.view.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdirector.view.ui.models.ModelResultDetails
import com.example.moviesdirector.view.ui.models.Result
import com.example.moviesdirector.view.ui.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val listMovies = MutableLiveData<List<Result>>()
    val errorMsg = MutableLiveData<String>()

    fun getListMovies() {
        val requestAPI = repository.getListMovies()

        requestAPI.enqueue(object : Callback<ModelResultDetails?> {
            override fun onResponse(
                call: Call<ModelResultDetails?>,
                response: Response<ModelResultDetails?>
            ) {
                val resultado = response.body()
                listMovies.postValue(resultado?.results)
            }

            override fun onFailure(call: Call<ModelResultDetails?>, t: Throwable) {
                errorMsg.postValue(t.message)
            }

        })
    }

}