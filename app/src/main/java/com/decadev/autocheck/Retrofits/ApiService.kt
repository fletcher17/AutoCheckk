package com.decadev.autocheck.Retrofits

import com.decadev.autocheck.Model.CarMediaLists.CarMedia
import com.decadev.autocheck.Model.Cars.CarsClass
import com.decadev.autocheck.Model.Detail.CarDetail
import com.decadev.autocheck.Model.logo.LogoClass
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("make?popular=true")
    suspend fun getAllLogos(): LogoClass

    @GET("car/search")
    suspend fun getAllCars(): CarsClass

    @GET("car/{cardId}")
    suspend fun getCarDetails(@Path("cardId") cardId: String): CarDetail

    @GET("car_media")
    suspend fun getCarMedia(@Query("carId") type: String) : CarMedia


}