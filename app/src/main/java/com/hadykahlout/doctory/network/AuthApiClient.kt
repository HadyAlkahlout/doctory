package com.hadykahlout.doctory.network

import APP_LANGUAGE
import com.hadykahlout.doctory.model.api.auth.ForgotPassword
import com.hadykahlout.doctory.model.api.response.APIResponse
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.model.api.auth.ResetPassword
import com.hadykahlout.doctory.model.api.auth.SignUpUser
import com.hadykahlout.doctory.model.api.auth.VerifyEmail
import com.hadykahlout.doctory.model.api.auth.VerifyResetCode
import com.hadykahlout.doctory.model.api.response.auth.CodeResponse
import com.hadykahlout.doctory.model.api.response.auth.ResetCodeResponse
import com.hadykahlout.doctory.model.api.response.auth.ServerUser
import com.hadykahlout.doctory.utils.SharedPrefsHelper
import retrofit2.Response
import retrofit2.http.*

interface AuthApiClient {

    // SignUp
    @POST("auth/register")
    suspend fun signupUser(
        @Body user: SignUpUser,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
    ): Response<APIResponse<CodeResponse>>

    // Login
    @POST("auth/login")
    suspend fun loginUser(
        @Body user: LoginUser,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
    ): Response<APIResponse<ServerUser>>

    // Google Login
    @GET("auth/google")
    suspend fun googleLogin(

    ): Response<APIResponse<Any>>

    // Verify Email
    @POST("auth/verify-email")
    suspend fun verifyEmail(
        @Body user: VerifyEmail,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
    ): Response<APIResponse<ServerUser>>

    // Forgot Password
    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Body forgotPassword: ForgotPassword,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
    ): Response<APIResponse<ResetCodeResponse>>

    // Resend Verification
    @POST("auth/resend-verification")
    suspend fun resendVerification(
        @Body resend: ForgotPassword,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
    ): Response<APIResponse<CodeResponse>>

    // Verify Reset Code
    @POST("auth/verify-reset-code")
    suspend fun verifyResetCode(
        @Body verifyResetCode: VerifyResetCode,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
    ): Response<APIResponse<Any>>

    // Reset Password
    @PUT("auth/reset-password")
    suspend fun resetPassword(
        @Body resetPassword: ResetPassword,
        @Header("accept-language") lang: String = SharedPrefsHelper.getString(APP_LANGUAGE, "en"),
    ): Response<APIResponse<Any>>

}