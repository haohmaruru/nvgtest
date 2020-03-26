package com.example.nvgtest.data.model.response

import com.google.gson.annotations.SerializedName

class Repository {
    var id = 0
    var name = ""
    var description = ""
    @SerializedName("stargazers_count")
    var stargazersCount = 0
    @SerializedName("watchers_count")
    var watchersCount = 0
    @SerializedName("forks_count")
    var forksCount = 0
    var language = ""
    @SerializedName("updated_at")
    var updatedAt:String = ""
    @SerializedName("html_url")
    var link = ""
    var pageNumber = 0
}