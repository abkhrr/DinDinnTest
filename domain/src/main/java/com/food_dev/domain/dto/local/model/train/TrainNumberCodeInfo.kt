package com.food_dev.domain.dto.local.model.train

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainNumberCodeInfo(
    @SerializedName("numberCode") var stationName: Int,
    @SerializedName("numberCodeTwo") var numberCodeTwo: Int,
    @SerializedName("numberCodeThree") var numberCodeThree: Int,
): Parcelable