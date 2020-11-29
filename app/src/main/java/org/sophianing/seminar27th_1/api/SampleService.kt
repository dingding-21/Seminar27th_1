package org.sophianing.seminar27th_1.api

import org.sophianing.seminar27th_1.data.RequestLoginData
import org.sophianing.seminar27th_1.data.RequestSignupData
import org.sophianing.seminar27th_1.data.ResponseLoginData
import org.sophianing.seminar27th_1.data.ResponseSignupData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SampleService {
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun postSignup(
        @Body body: RequestSignupData
    ):Call<ResponseSignupData>

    @Headers("Content-Type:application/json")
    @POST("/users/signin")

    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>
}

