package com.hadykahlout.doctory.ui.fragment.auth

import android.content.Context
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialRequest.Builder
import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.hadykahlout.doctory.data.AuthRepository
import com.hadykahlout.doctory.model.api.auth.ForgotPassword
import com.hadykahlout.doctory.model.api.response.APIResponse
import com.hadykahlout.doctory.model.api.auth.LoginUser
import com.hadykahlout.doctory.model.api.auth.ResetPassword
import com.hadykahlout.doctory.model.api.auth.SignUpUser
import com.hadykahlout.doctory.model.api.auth.VerifyEmail
import com.hadykahlout.doctory.model.api.auth.VerifyResetCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AuthViewModel : ViewModel() {
    private val TAG = "AuthViewModel"

    private val repository = AuthRepository()

    val signUpData = MutableLiveData<Response<APIResponse<Any>>>()
    val loginData = MutableLiveData<Response<APIResponse<Any>>>()
    val googleData = MutableLiveData<Response<APIResponse<Any>>>()
    val verifyEmailData = MutableLiveData<Response<APIResponse<Any>>>()
    val forgotPasswordData = MutableLiveData<Response<APIResponse<Any>>>()
    val resendVerificationData = MutableLiveData<Response<APIResponse<Any>>>()
    val verifyResetCodeData = MutableLiveData<Response<APIResponse<Any>>>()
    val resetPasswordData = MutableLiveData<Response<APIResponse<Any>>>()

    fun signUp(user: SignUpUser){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.signupUser(user)
            withContext(Dispatchers.Main){
                signUpData.value = response
            }
        }
    }

    fun login(user: LoginUser){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.loginUser(user)
            withContext(Dispatchers.Main){
                loginData.value = response
            }
        }
    }

    fun googleLogin(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.googleLogin()
            withContext(Dispatchers.Main){
                googleData.value = response
            }
        }
    }

    fun verifyEmail(verifyEmail: VerifyEmail){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.verifyEmail(verifyEmail)
            withContext(Dispatchers.Main){
                verifyEmailData.value = response
            }
        }
    }

    fun forgotPassword(forgotPassword: ForgotPassword){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.forgotPassword(forgotPassword)
            withContext(Dispatchers.Main){
                forgotPasswordData.value = response
            }
        }
    }

    fun resendVerification(resend: ForgotPassword){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.resendVerification(resend)
            withContext(Dispatchers.Main){
                resendVerificationData.value = response
            }
        }
    }

    fun verifyResetCode(verifyResetCode: VerifyResetCode){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.verifyResetCode(verifyResetCode)
            withContext(Dispatchers.Main){
                verifyResetCodeData.value = response
            }
        }
    }

    fun resetPassword(resetPassword: ResetPassword){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.resetPassword(resetPassword)
            withContext(Dispatchers.Main){
                resetPasswordData.value = response
            }
        }
    }

//    fun authWithGoogle(context: Context) {
//        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
//            .setFilterByAuthorizedAccounts(true)
//            .setServerClientId("5678997989694")
//            .setAutoSelectEnabled(true)
//            .setNonce("HDHD261120")
//            .build()
//
//        val request: GetCredentialRequest = Builder()
//                .addCredentialOption(googleIdOption)
//                .build()
//
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val result = credentialManager.getCredential(
//                    request = request,
//                    context = context,
//                )
//                handleSignIn(result)
//            } catch (e: GetCredentialException) {
//                handleFailure(e)
//            }
//        }
//    }
//
//    fun handleSignIn(result: GetCredentialResponse) {
//        // Handle the successfully returned credential.
//        val credential = result.credential
//
//        when (credential) {
//
//            // Passkey credential
//            is PublicKeyCredential -> {
//                // Share responseJson such as a GetCredentialResponse on your server to
//                // validate and authenticate
//                responseJson = credential.authenticationResponseJson
//            }
//
//            // Password credential
//            is PasswordCredential -> {
//                // Send ID and password to your server to validate and authenticate.
//                val username = credential.id
//                val password = credential.password
//            }
//
//            // GoogleIdToken credential
//            is CustomCredential -> {
//                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
//                    try {
//                        // Use googleIdTokenCredential and extract id to validate and
//                        // authenticate on your server.
//                        val googleIdTokenCredential = GoogleIdTokenCredential
//                            .createFrom(credential.data)
//                    } catch (e: GoogleIdTokenParsingException) {
//                        Log.e(TAG, "Received an invalid google id token response", e)
//                    }
//                } else {
//                    // Catch any unrecognized custom credential type here.
//                    Log.e(TAG, "Unexpected type of credential")
//                }
//            }
//
//            else -> {
//                // Catch any unrecognized credential type here.
//                Log.e(TAG, "Unexpected type of credential")
//            }
//        }
//    }

}