package com.hadykahlout.doctory.model.api.response.auth

import com.google.gson.annotations.SerializedName

data class ServerUser(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("avatar") var avatar: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("mobile") var mobile: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("password_updated_at") var passwordUpdatedAt: String? = null,
    @SerializedName("password_reset_code") var passwordResetCode: String? = null,
    @SerializedName("password_reset_code_expires") var passwordResetCodeExpires: Boolean? = null,
    @SerializedName("password_reset_verified") var passwordResetVerified: Boolean? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("date_of_birth") var dateOfBirth: String? = null,
    @SerializedName("role_id") var roleId: Int? = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("is_email_verified") var isEmailVerified: Boolean? = null,
    @SerializedName("email_verification_code") var emailVerificationCode: String? = null,
    @SerializedName("email_verification_code_expires") var emailVerificationCodeExpires: String? = null,
    @SerializedName("email_verified_at") var emailVerifiedAt: String? = null,
    @SerializedName("is_mobile_verified") var isMobileVerified: Boolean? = null,
    @SerializedName("mobile_verification_code") var mobileVerificationCode: String? = null,
    @SerializedName("mobile_verification_code_expires") var mobileVerificationCodeExpires: String? = null,
    @SerializedName("mobile_verified_at") var mobileVerifiedAt: String? = null,
    @SerializedName("is_social_login") var isSocialLogin: Boolean? = null,
    @SerializedName("social_login_type") var socialLoginType: String? = null,
    @SerializedName("social_login_id") var socialLoginId: String? = null,
    @SerializedName("social_login_token") var socialLoginToken: String? = null,
    @SerializedName("social_login_token_expires") var socialLoginTokenExpires: Boolean? = null,
    @SerializedName("social_login_token_updated_at") var socialLoginTokenUpdatedAt: String? = null,
    @SerializedName("is_blocked") var isBlocked: Boolean? = null,
    @SerializedName("is_enabled") var isEnabled: Boolean? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("deleted_at") var deletedAt: String? = null,
    @SerializedName("access_token") var accessToken: String? = null

)
