package com.example.compose

import com.example.xml.R
import com.example.xml.models.Game

sealed class DummyData(val items : List<Game>) {
    object Card : DummyData(items = listOf(        Game(
            id = 0,
            imgResource = R.drawable.ss,
            titleResource = R.string.title_towerDefenseX,
            descResource = R.string.desc_towerDefenseX,
            wikiUri = "https://tdx.fandom.com/wiki/Tower_Defense_X_Wiki"
        ),
        Game(
            id = 1,
            imgResource = R.drawable.ss2,
            titleResource = R.string.title_unknownThreat,
            descResource = R.string.desc_unknownThreat,
            wikiUri = "https://unknownthreat.fandom.com/wiki/Unknown_Threat_Wiki"
        ),
        Game(
            id = 2,
            imgResource = R.drawable.ss3,
            titleResource = R.string.title_stronghold,
            descResource = R.string.desc_stronghold,
            wikiUri = "https://stronghold.fandom.com/wiki/Stronghold_Crusader"
        ),
        Game(
            id = 3,
            imgResource = R.drawable.ss4,
            titleResource = R.string.title_mountAndBlade,
            descResource = R.string.desc_mountAndBlade,
            wikiUri = "https://mountandblade.fandom.com/wiki/Mount%26Blade:_Warband"
        ),
        Game(
            id = 4,
            imgResource = R.drawable.ss5,
            titleResource = R.string.title_residentEvil,
            descResource = R.string.desc_residentEvil,
            wikiUri = "https://residentevil.fandom.com/wiki/Resident_Evil_franchise"
        )
    ))
}