package com.gemi_droid.linkdev_workshop.model.network.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsModel protected constructor(`in`: Parcel) : Parcelable {


    @SerializedName("author")
    @Expose
    var newsAuthor: String

    @SerializedName("title")
    @Expose
    var newsTitle: String

    @SerializedName("description")
    @Expose
    var newsDescription: String

    @SerializedName("url")
    @Expose
    var newsUrl: String

    @SerializedName("urlToImage")
    @Expose
    var newsImageUrl: String

    @SerializedName("publishedAt")
    @Expose
    var newsDate: String



    init {
        newsImageUrl = `in`.readString()
        newsAuthor = `in`.readString()
        newsTitle = `in`.readString()
        newsDescription = `in`.readString()
        newsDate = `in`.readString()
        newsUrl = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(newsImageUrl)
        parcel.writeString(newsAuthor)
        parcel.writeString(newsTitle)
        parcel.writeString(newsDescription)
        parcel.writeString(newsDate)
        parcel.writeString(newsUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NewsModel> = object : Parcelable.Creator<NewsModel> {
            override fun createFromParcel(`in`: Parcel): NewsModel {
                return NewsModel(`in`)
            }

            override fun newArray(size: Int): Array<NewsModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
