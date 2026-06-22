package com.example.xml.utils

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "movie_detail_table",
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MovieDetailEntity(
    @PrimaryKey
    val movieId: Int,
    val runtime: Int,
    val tagline: String?,
    val budget: Long,
    val homepage: String?
)

data class MovieWithDetails(
    @Embedded
    val movie: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val details: MovieDetailEntity?
)