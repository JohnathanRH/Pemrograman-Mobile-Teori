package com.example.xml.models

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id : Int?,
    val imgResource : Int,
    val titleResource : Int,
    val descResource : Int,
    val wikiUri : String
)