package com.example.xml.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow<GameUiState>(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent: SharedFlow<NavigationEvent> = _navigationEvent.asSharedFlow()
    fun onSettingClicked() {
        viewModelScope.launch { _navigationEvent.emit(NavigationEvent.NavigateToSetting) }
    }

    fun onHomeClicked() {
        viewModelScope.launch { _navigationEvent.emit(NavigationEvent.NavigateToHome) }
    }

    fun onWikiClicked(url: String?) {
        url?.let {
            viewModelScope.launch { _navigationEvent.emit(NavigationEvent.OpenWiki(it)) }
        }
    }

    fun onLanguageSelected(tag: String) {
        viewModelScope.launch { _navigationEvent.emit(NavigationEvent.ChangeLanguage(tag)) }
    }
}