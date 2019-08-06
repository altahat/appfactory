package datanapps.retrofitkotlin.services.network

import datanapps.retrofitkotlin.services.users.model.BaseUser
import datanapps.retrofitkotlin.services.users.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * API for getting weather from https://darksky.net/
 */
interface APIUser {

    //This call will return a full list of users
    @GET("fortniteusers")
    fun getUserList(@QueryMap options: Map<String, String>): Call<BaseUser>

    //Use this call to make individual user requests.
    @GET("fortniteusers/{id}")
    fun getUser(@Path("id") id: Int, @QueryMap options: Map<String, String>): Call<User>
}
