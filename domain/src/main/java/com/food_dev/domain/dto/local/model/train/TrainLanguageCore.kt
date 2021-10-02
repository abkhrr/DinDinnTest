package com.food_dev.domain.dto.local.model.train

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainLanguageCore(
    @SerializedName("stationName") var stationName: String,
    @SerializedName("chineseeStationName") var chineseStationName: String,
    @SerializedName("tamilStationName") var tamilStationName: String,
): Parcelable