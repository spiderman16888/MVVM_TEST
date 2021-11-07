package xyz.markapp.markmvvmtest1.network


import com.google.gson.JsonArray

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.markapp.markmvvmtest1.model.User

interface ApiDataService {

/*    @get:GET("users")
    val apiRequestsArray: Call<JsonArray>

    @GET("/users?per_page=100")
    fun getTasks(): Call<JsonArray>?*/

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/users")
    fun getUsers(
        @Query("per_page") per_page: Int,
        @Query("since") since: Int
    ): Call<JsonArray>?


    //---
//    @Headers("Accept: application/vnd.github.v3+json")
//    @GET("/users/{user}")
//    fun getUserDetail(@Path("user") String user): Call<JsonArray>?
//
    //--
    @Headers("Accept: application/vnd.github.v3.full+json", "User-Agent: Retrofit-Sample-App")
    @GET("users/{username}")
    open fun getUser(@Path("username") username: String?): Call<User?>?

}
