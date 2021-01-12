package com.example.androidtestapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.util.*

class Result() : Parcelable {

    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null

    @SerializedName("price")
    @Expose
    private var price: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("uid")
    @Expose
    private var uid: String? = null

    @SerializedName("image_ids")
    @Expose
    private var imageIds: List<String?>? = null

    @SerializedName("image_urls")
    @Expose
    private var imageUrls: List<String?>? = null

    @SerializedName("image_urls_thumbnails")
    @Expose
    private var imageUrlsThumbnails: List<String?>? = null

    constructor(parcel: Parcel) : this() {
        createdAt = parcel.readString()
        price = parcel.readString()
        name = parcel.readString()
        uid = parcel.readString()
        imageIds = parcel.createStringArrayList()
        imageUrls = parcel.createStringArrayList()
        imageUrlsThumbnails = parcel.createStringArrayList()
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }

    @ExperimentalStdlibApi
    fun getName(): String? {
        return name?.capitalize(Locale.ENGLISH)
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getUid(): String? {
        return uid
    }

    fun setUid(uid: String?) {
        this.uid = uid
    }

    fun getImageIds(): List<String?>? {
        return imageIds
    }

    fun setImageIds(imageIds: List<String?>?) {
        this.imageIds = imageIds
    }

    fun getImageUrls(): List<String?>? {
        return imageUrls
    }

    fun setImageUrls(imageUrls: List<String?>?) {
        this.imageUrls = imageUrls
    }

    fun getImageUrlsThumbnails(): List<String?>? {
        return imageUrlsThumbnails
    }

    fun setImageUrlsThumbnails(imageUrlsThumbnails: List<String?>?) {
        this.imageUrlsThumbnails = imageUrlsThumbnails
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(createdAt)
        dest?.writeString(price)
        dest?.writeString(name)
        dest?.writeString(uid)
        dest?.writeStringList(imageIds)
        dest?.writeStringList(imageUrls)
        dest?.writeStringList(imageUrlsThumbnails)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}