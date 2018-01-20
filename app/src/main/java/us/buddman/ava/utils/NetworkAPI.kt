package us.buddman.ava.utils


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Junseok Oh on 2017-07-18.
 */

interface NetworkAPI {

    @POST("/user/register")
    @FormUrlEncoded
    fun registerUser(@Header("header") token: String, @Field("user_email") email : String, @Field("user_password") password : String, @Field("user_nick") nickname : String, @Field("user_phone") phone : String): Call<ResponseBody>

    @POST("/user/login")
    @FormUrlEncoded
    fun loginUser(@Header("header") token: String, @Field("user_email") email : String, @Field("user_password") password : String): Call<ResponseBody>

    @POST("/user/facebook")
    @FormUrlEncoded
    fun facebookAuthorize(@Field("access_token") token : String): Call<ResponseBody>

    @POST("/user/authenticate")
    @FormUrlEncoded
    fun authenticateUser(@Field("user_token") token : String): Call<ResponseBody>

    @POST("/user/profile")
    @FormUrlEncoded
    fun getProfile(@Header("token") token : String): Call<ResponseBody>

}