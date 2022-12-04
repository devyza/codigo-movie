package com.example.codigotravel.data.source.remote.dto


data class MovieListResponse (
    val results: List<Movie>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
)

data class Movie (
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String?,
    val popularity: String,
    val vote_count: String,
    val video: Boolean,
    val vote_average: Int,
    val dates: Dates
)

data class Dates(
    val maximum: String,
    val minimum: String,
)

