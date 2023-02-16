package com.inlay.concertswatcher.presentation.search.models

data class SearchUiModel(
    val body: String,
    val name: String,
    val minDate: String?,
    val maxDate: String?
)