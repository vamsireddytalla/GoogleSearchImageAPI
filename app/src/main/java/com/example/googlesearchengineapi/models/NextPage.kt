package com.example.googlesearchengineapi.models

data class NextPage(
    val count: Int,
    val cx: String,
    val inputEncoding: String,
    val outputEncoding: String,
    val safe: String,
    val searchTerms: String,
    val searchType: String,
    val startIndex: Int,
    val title: String,
    val totalResults: String
)