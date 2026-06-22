package com.example.xml.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.xml.R
import com.example.xml.databinding.SettingBinding
import com.example.xml.viewmodels.GameViewModel
import com.example.xml.viewmodels.NavigationEvent
import kotlinx.coroutines.launch

class Setting : Fragment(R.layout.setting) {

    private val mainViewModel: GameViewModel by activityViewModels()
    private lateinit var binding: SettingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingBinding.bind(view)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.navigationEvent.collect { event ->
                    if (event is NavigationEvent.ChangeLanguage) {
                        AppCompatDelegate.setApplicationLocales(
                            LocaleListCompat.forLanguageTags(event.languageTag)
                        )
                    }
                }
            }
        }
    }
}