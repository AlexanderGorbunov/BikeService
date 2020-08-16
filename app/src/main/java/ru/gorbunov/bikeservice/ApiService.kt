package ru.gorbunov.bikeservice

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import ru.gorbunov.bikeservice.Model.Bike
import ru.gorbunov.bikeservice.Model.Constants
import java.util.*

interface ApiService {

    @GET("/challenge/getinfo")
    fun getBikeStatus(
        @Query("code") code: String,
        @Query("ApiKey") key: String) : Observable<Bike>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}