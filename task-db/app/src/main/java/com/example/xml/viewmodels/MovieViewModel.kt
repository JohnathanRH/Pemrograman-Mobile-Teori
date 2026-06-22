package com.example.xml.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xml.utils.MovieEntity
import com.example.xml.utils.MovieDetailEntity
import com.example.xml.utils.MovieRepository
import com.example.xml.utils.NetworkResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class NavigationEvent {
    object NavigateToSetting : NavigationEvent()
    object NavigateToHome : NavigationEvent()
    data class OpenWiki(val url: String) : NavigationEvent()
    data class ChangeLanguage(val languageTag: String) : NavigationEvent()
}

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _moviesState = MutableStateFlow<NetworkResult<List<MovieEntity>>>(NetworkResult.Loading)
    val moviesState: StateFlow<NetworkResult<List<MovieEntity>>> = _moviesState

    private val _movieDetailState = MutableStateFlow<NetworkResult<MovieDetailEntity>>(NetworkResult.Loading)
    val movieDetailState: StateFlow<NetworkResult<MovieDetailEntity>> = _movieDetailState

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent: SharedFlow<NavigationEvent> = _navigationEvent

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            repository.getPopularMovies().collect { result ->
                _moviesState.value = result
            }
        }
    }

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetails(movieId).collect { result ->
                _movieDetailState.value = result
            }
        }
    }

    fun onWikiClicked(url: String) {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.OpenWiki(url))
        }
    }

    fun onHomeClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.NavigateToHome)
        }
    }

    fun onSettingClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.NavigateToSetting)
        }
    }
}
