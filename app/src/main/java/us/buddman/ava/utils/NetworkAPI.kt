package us.buddman.ava.utils


import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import us.buddman.ava.models.Ah
import us.buddman.ava.models.Comment
import us.buddman.ava.models.Da
import us.buddman.ava.models.User

/**
 * Created by Junseok Oh on 2017-07-18.
 */

interface NetworkAPI {

    @GET("/facebook/app")
    fun facebookLogin(@Query("access_token") fbToken: String): Call<User>


    @POST("/auth/login")
    @FormUrlEncoded
    fun localLogin(@Field("id") id: String, @Field("password") password: String): Call<User>

    @POST("/auth/auto")
    @FormUrlEncoded
    fun autoLogin(@Field("token") token: String): Call<User>

    @POST("/auth/register")
    @FormUrlEncoded
    fun localRegister(@Field("username") username: String, @Field("id") id: String, @Field("password") password: String): Call<User>

    @POST("/auth/edituser")
    @FormUrlEncoded
    fun editUser(@Field("username") username: String, @Field("id") id: String, @Field("password") password: String): Call<User>

    @Multipart
    @POST("/auth/edituser/img")
    fun editUserImage(
            @Part("id") id: RequestBody,
            @Part("profile_img"
            ) profileImg: RequestBody): Call<ResponseBody>

    @Multipart
    @POST("/ah/post/add")
    @FormUrlEncoded
    fun addAhPost(
            @Part("username") username: RequestBody,
            @Part("user_token") userToken: RequestBody,
            @Part("title") title: RequestBody,
            @Part("text") text: RequestBody,
            @Part("file") photo: RequestBody
    ): Call<ResponseBody>

    @POST("/ah/post/list")
    fun getAhPostList(): Call<ArrayList<Ah>>

    @POST("/ah/post/myah")
    @FormUrlEncoded
    fun getMyAhPostList(@Field("user_token") userToken: String): Call<ArrayList<Ah>>

    @POST("/ah/post/view")
    @FormUrlEncoded
    fun getAhPostDetail(@Field("post_token") postToken: String): Call<Ah>

    @POST("/ah/post/like")
    @FormUrlEncoded
    fun likeAhPost(@Field("user_token") userToken: String, @Field("post_token") postToken: String): Call<ResponseBody>

    @POST("/ah/post/rank")
    fun getAh5thList(): Call<ArrayList<Ah>>

    @POST("/ah/comment/add")
    @FormUrlEncoded
    fun addCommentAh(@Field("username") username: String,
                     @Field("user_token") userToken: String,
                     @Field("text") text: String,
                     @Field("post_token") postToken: String
    ): Call<ResponseBody>

    @POST("/ah/comment/view")
    @FormUrlEncoded
    fun getAhCommentList(@Field("post_token") postToken: String
    ): Call<ArrayList<Comment>>

    @POST("/da/post/add")
    @FormUrlEncoded
    fun addDaPost(@Field("username") username: String,
                  @Field("user_token") userToken: String,
                  @Field("title") title: String,
                  @Field("text") text: String
    ): Call<ResponseBody>

    @POST("/da/post/myda")
    @FormUrlEncoded
    fun getMyDaPostList(@Field("user_token") userToken: String): Call<ResponseBody>

    @POST("/da/post/view")
    @FormUrlEncoded
    fun getDaPostDetail(@Field("post_token") postToken: String): Call<Da>

    @POST("/da/post/like")
    @FormUrlEncoded
    fun likeDaPost(@Field("user_token") userToken: String, @Field("post_token") postToken: String): Call<ResponseBody>

    @POST("/da/post/rank")
    fun getDa5thList(): Call<ArrayList<Da>>

    @POST("/da/post/search")
    @FormUrlEncoded
    fun searchDa(@Field("title") title : String): Call<ArrayList<Da>>

}