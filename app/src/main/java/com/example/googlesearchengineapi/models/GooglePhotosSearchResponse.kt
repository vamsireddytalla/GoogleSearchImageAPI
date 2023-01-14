package com.example.googlesearchengineapi.models

data class GooglePhotosSearchResponse(
    val context: Context,
    val items: List<Item>,
    val kind: String,
    val queries: Queries,
    val searchInformation: SearchInformation,
    val url: Url
)