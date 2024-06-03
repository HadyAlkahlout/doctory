package com.hadykahlout.doctory.network

import com.hadykahlout.doctory.model.api.auth.ForgotPassword
import com.hadykahlout.doctory.model.api.response.APIResponse
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.model.api.auth.ResetPassword
import com.hadykahlout.doctory.model.api.auth.SignUpUser
import com.hadykahlout.doctory.model.api.auth.VerifyEmail
import com.hadykahlout.doctory.model.api.auth.VerifyResetCode
import retrofit2.Response
import retrofit2.http.*

interface AuthApiClient {

    // SignUp
    @POST("auth/register")
    suspend fun signupUser(
        @Body user: SignUpUser
    ): Response<APIResponse<Any>>

    // Login
    @POST("auth/login")
    suspend fun loginUser(
        @Body user: LoginUser
    ): Response<APIResponse<Any>>

    // Google Login
    @GET("auth/google")
    suspend fun googleLogin(

    ): Response<APIResponse<Any>>

    // Verify Email
    @POST("auth/verify-email")
    suspend fun verifyEmail(
        @Body user: VerifyEmail
    ): Response<APIResponse<Any>>

    // Forgot Password
    @POST("auth/forgot-password")
    suspend fun forgotPassword(
        @Body forgotPassword: ForgotPassword
    ): Response<APIResponse<Any>>

    // Resend Verification
    @POST("auth/resend-verification")
    suspend fun resendVerification(
        @Body resend: ForgotPassword
    ): Response<APIResponse<Any>>

    // Verify Reset Code
    @POST("auth/verify-reset-code")
    suspend fun verifyResetCode(
        @Body verifyResetCode: VerifyResetCode
    ): Response<APIResponse<Any>>

    // Reset Password
    @PUT("auth/reset-password")
    suspend fun resetPassword(
        @Body resetPassword: ResetPassword
    ): Response<APIResponse<Any>>

}