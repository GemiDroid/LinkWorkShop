package com.gemi_droid.linkdev_workshop.model.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsResponse {


    @SerializedName("articles")
    @Expose
     lateinit var NewsResutlsList: List<NewsModel>





}