package com.decadev.autocheck.Repository

import com.decadev.autocheck.Model.CarMediaLists.CarMedia
import com.decadev.autocheck.Model.Cars.CarsClass
import com.decadev.autocheck.Model.Detail.CarDetail
import com.decadev.autocheck.Model.logo.LogoClass
import com.decadev.autocheck.Retrofits.RetrofitClient.apiService

class MainRepository {

    suspend fun getAllLogoFromApi(): LogoClass {
        var res = apiService.getAllLogos()
        return res
    }

    suspend fun getAllCarsFromApi(): CarsClass {
        var res = apiService.getAllCars()
        return res
    }

    suspend fun getCarDetailsFromApi(carId: String): CarDetail {
        var resultOfCarDetails = apiService.getCarDetails(carId)

        return resultOfCarDetails
    }

    suspend fun getCarMediaFromApi(carid: String) : CarMedia {
        var resultOfCarMedia = apiService.getCarMedia(carid)
        return resultOfCarMedia
    }
}