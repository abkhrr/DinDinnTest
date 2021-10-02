package com.food_dev.domain.dto.local.model.train

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
class SingaporeTrain(
    @SerializedName("_id") var id: Int,
    @SerializedName("languageCore") var trainLanguageCore: TrainLanguageCore,
    @SerializedName("stationLocation") var stationLocation: String,
    @SerializedName("stationType") var stationType: String,
    @SerializedName("lines") var lines: String,
    @SerializedName("alphaNumericInfo") var trainAlphanumericInfo: TrainAlphanumericInfo,
    @SerializedName("numberCodeInfo") var trainNumberCodeInfo: TrainNumberCodeInfo,
    @SerializedName("stationFullAddress") var stationFullAddress: String,
): Parcelable