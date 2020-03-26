package com.example.nvgtest.data.model.response

import com.google.gson.annotations.SerializedName

class Profile {
    var id: Int = 0
    @SerializedName("avatar_url")
    var avatarUrl = ""
    var name = ""
    var email = ""
    var blog = ""
    @SerializedName("public_repos")
    var publicRepos = 0
}