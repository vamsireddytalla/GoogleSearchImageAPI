package com.example.googlesearchengineapi.models

data class Queries(
    val nextPage: List<NextPage>,
    val request: List<Request>
)