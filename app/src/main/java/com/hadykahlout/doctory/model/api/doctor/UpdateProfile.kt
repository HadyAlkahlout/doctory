package com.hadykahlout.doctory.model.api.doctor

import com.google.gson.annotations.SerializedName

data class UpdateProfile(
    @SerializedName("username")
    val username: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("province")
    val province: String,
    @SerializedName("address")
    val address: String,
)
