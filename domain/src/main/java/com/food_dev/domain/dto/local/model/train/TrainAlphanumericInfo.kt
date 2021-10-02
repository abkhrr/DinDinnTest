package com.food_dev.domain.dto.local.model.train

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainAlphanumericInfo(
    @SerializedName("alphaNumeric") var stationName: String,
    @SerializedName("alphaNumericTwo") var alphaNumericTwo: String,
    @SerializedName("alphaNumericThree") var alphaNumericThree: String,
): Parcelable