package com.example.googlefilechunks.data

import com.example.googlesearchengineapi.models.GooglePhotosSearchResponse
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("v1")
    fun searchItem(@Query("key") key:String,
                   @Query("cx") cx:String,
                   @Query("q") q:String,
                   @Query("searchType") searchType:String,
                   @Query("start") start:String):Call<GooglePhotosSearchResponse>

}