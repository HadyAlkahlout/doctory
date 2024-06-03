package com.hadykahlout.doctory.data

import com.hadykahlout.doctory.model.api.auth.ForgotPassword
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.model.api.auth.ResetPassword
import com.hadykahlout.doctory.model.api.auth.SignUpUser
import com.hadykahlout.doctory.model.api.auth.VerifyEmail
import com.hadykahlout.doctory.model.api.auth.VerifyResetCode
import com.hadykahlout.doctory.network.ApiService

class AuthRepository {

    suspend fun signupUser(
        user: SignUpUser
    ) = ApiService.authClient.signupUser(user)

    suspend fun loginUser(
        user: LoginUser
    ) = ApiService.authClient.loginUser(user)

    suspend fun googleLogin(

    ) = ApiService.authClient.googleLogin()

    suspend fun verifyEmail(
        verify: VerifyEmail
    ) = ApiService.authClient.verifyEmail(verify)

    suspend fun forgotPassword(
        forgotPassword: ForgotPassword
    ) = ApiService.authClient.forgotPassword(forgotPassword)

    suspend fun resendVerification(
        resend: ForgotPassword
    ) = ApiService.authClient.resendVerification(resend)

    suspend fun verifyResetCode(
        verifyResetCode: VerifyResetCode
    ) = ApiService.authClient.verifyResetCode(verifyResetCode)

    suspend fun resetPassword(
        resetPassword: ResetPassword
    ) = ApiService.authClient.resetPassword(resetPassword)

}