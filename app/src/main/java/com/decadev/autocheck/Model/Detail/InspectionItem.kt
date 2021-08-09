package com.decadev.autocheck.Model.Detail

data class InspectionItem(
    val comment: String,
    val medias: List<Media>,
    val name: String,
    val response: String
)