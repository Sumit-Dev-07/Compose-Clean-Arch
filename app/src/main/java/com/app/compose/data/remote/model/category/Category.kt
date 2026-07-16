package com.app.compose.data.remote.model.category

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
