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

    @POST("/auth/login")
    @FormUrlEncoded
    fun localLogin(@Path("id") id: String, @Path("password") password: String): Call<User>

    @POST("/auth/register")
    @FormUrlEncoded
    fun localRegister(@Path("username") username: String, @Path("id") id: String, @Path("password") password: String): Call<User>

    @POST("/auth/edituser")
    @FormUrlEncoded
    fun editUser(@Path("username") username: String, @Path("id") id: String, @Path("password") password: String): Call<User>

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
    fun getMyAhPostList(@Path("user_token") userToken: String): Call<ArrayList<Ah>>

    @POST("/ah/post/view")
    @FormUrlEncoded
    fun getAhPostDetail(@Path("post_token") postToken: String): Call<Ah>

    @POST("/ah/post/like")
    @FormUrlEncoded
    fun likeAhPost(@Path("user_token") userToken: String, @Path("post_token") postToken: String): Call<ResponseBody>

    @POST("/ah/post/rank")
    fun getAh5thList(): Call<ArrayList<Ah>>

    @POST("/ah/comment/add")
    @FormUrlEncoded
    fun addCommentAh(@Path("username") username: String,
                     @Path("user_token") userToken: String,
                     @Path("text") text: String,
                     @Path("post_token") postToken: String
    ): Call<ResponseBody>

    @POST("/ah/comment/view")
    @FormUrlEncoded
    fun getAhCommentList(@Path("post_token") postToken: String
    ): Call<ArrayList<Comment>>

    @POST("/da/post/add")
    @FormUrlEncoded
    fun addDaPost(@Path("username") username: String,
                  @Path("user_token") userToken: String,
                  @Path("title") title: String,
                  @Path("text") text: String
    ): Call<ResponseBody>

    @POST("/da/post/myda")
    @FormUrlEncoded
    fun getMyDaPostList(@Path("user_token") userToken: String): Call<ResponseBody>

    @POST("/da/post/view")
    @FormUrlEncoded
    fun getDaPostDetail(@Path("post_token") postToken: String): Call<Da>

    @POST("/da/post/like")
    @FormUrlEncoded
    fun likeDaPost(@Path("user_token") userToken: String, @Path("post_token") postToken: String): Call<ResponseBody>

    @POST("/da/post/rank")
    fun getDa5thList(): Call<ArrayList<Da>>

    @POST("/da/post/search")
    @FormUrlEncoded
    fun searchDa(@Path("title") title : String): Call<ArrayList<Da>>

}