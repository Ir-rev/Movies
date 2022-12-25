package com.example.movies.ui.genresFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.response.Film
import com.example.movies.response.FilmsList
import com.example.movies.rest.MoviesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresFragmentViewModel : ViewModel() {
    private val TAG = "checkResult"
    private val repository = MoviesRepository()

    private val _genresListLiveData = MutableLiveData<FilmsList>()
    val genresListLiveData: LiveData<FilmsList> = _genresListLiveData

    /* шина для общения вьюмодели и фрагмента */
    val actions = MutableSharedFlow<Actions>()

    init {
        checkIfFilmsListIsEmpty()
    }

    fun checkIfFilmsListIsEmpty() {
        repository.getFilmsList().enqueue(object : Callback<FilmsList> {
            override fun onResponse(call: Call<FilmsList>, response: Response<FilmsList>) {
                val currentResponse = response.body()
                if (currentResponse != null) {
                    _genresListLiveData.postValue(response.body().also {
                        it?.films?.forEach { film ->
                            film.onClick = {
                                viewModelScope.launch {
                                    actions.emit(Actions.OpenNewFragment(film))
                                }
                            }
                        }
                    })
                } else {
                    Log.d(TAG, "Ошибка выгрузки списка фильмов")
                }
            }

            override fun onFailure(call: Call<FilmsList>, t: Throwable) {
                Log.d(TAG, "Ошибка загрузки", t)
            }
        })
    }


    sealed class Actions {
        data class OpenNewFragment(val film: Film) : Actions()
        data class ShowMessage(val message: String) : Actions()
    }
}

