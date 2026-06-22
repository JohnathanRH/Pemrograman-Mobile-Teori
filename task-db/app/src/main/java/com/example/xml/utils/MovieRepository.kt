package com.example.xml.utils

import io.ktor.client.request.*
import kotlinx.coroutines.flow.*
import com.example.xml.models.MovieResponse
import io.ktor.client.call.body

class MovieRepository(
    private val movieDao: MovieDao,
    private val apiKey: String = "baa7033988e4a440b53a833c6af66f3f"
) {

    fun getPopularMovies(): Flow<NetworkResult<List<MovieEntity>>> = flow {
        emit(NetworkResult.Loading)

        val localData = movieDao.getAllMovies().first()
        if (localData.isNotEmpty()) {
            emit(NetworkResult.Success(localData))
        }

        try {
            val response: MovieResponse = KtorClient.client.get("https://api.themoviedb.org/3/movie/popular") {
                parameter("api_key", apiKey)
            }.body()

            val entities = response.results.map { dto ->
                MovieEntity(
                    id = dto.id,
                    title = dto.title,
                    overview = dto.overview,
                    posterPath = "https://image.tmdb.org/t/p/w500${dto.posterPath}",
                    releaseDate = dto.releaseDate
                )
            }

            movieDao.clearMovies()
            movieDao.insertMovies(entities)

            emit(NetworkResult.Success(entities))

        } catch (e: Exception) {
            android.util.Log.e("MOVIE_ERROR", "API Details Call Failed!", e)
            if (localData.isNotEmpty()) {
                emit(NetworkResult.Success(localData))
            } else {
                emit(NetworkResult.Error("Gagal memuat data dari server dan tidak ada cache.", e))
            }
        }
    }

    fun getMovieDetails(movieId: Int): Flow<NetworkResult<MovieDetailEntity>> = flow {
        emit(NetworkResult.Loading)

        val localDetails = movieDao.getMovieWithDetailsById(movieId).first()?.details
        if (localDetails != null) {
            emit(NetworkResult.Success(localDetails))
        }

        try {
            val response: MovieDetailResponse = KtorClient.client.get("https://api.themoviedb.org/3/movie/$movieId") {
                parameter("api_key", apiKey)
            }.body()

            val detailEntity = MovieDetailEntity(
                movieId = response.id,
                runtime = response.runtime,
                tagline = response.tagline,
                budget = response.budget,
                homepage = response.homepage
            )

            movieDao.insertMovieDetail(detailEntity)
            emit(NetworkResult.Success(detailEntity))

        } catch (e: Exception) {
            if (localDetails != null) {
                emit(NetworkResult.Success(localDetails))
            } else {
                emit(NetworkResult.Error("Gagal memuat detail film.", e))
            }
        }
    }
}