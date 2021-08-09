package com.decadev.autocheck.Model.Detail

data class Model(
    val id: Int,
    val imageUrl: String,
    val make: Make,
    val modelFeatures: List<Any>,
    val name: String,
    val popular: Boolean,
    val wheelType: String
)