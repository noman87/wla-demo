package com.purevpn.service.location

import com.purevpn.core.errors.Errors
import com.purevpn.core.exceptions.ApiException
import com.purevpn.core.exceptions.AppException
import com.purevpn.core.iNetwork.ILocationNetwork
import com.purevpn.core.iRepository.ILocationRepository
import com.purevpn.core.iService.ILocationService
import com.purevpn.core.models.LocationModel
import com.purevpn.core.models.Result
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class LocationServiceImpl :
    ILocationService, KoinComponent, BaseService() {

    private val locationRepository: ILocationRepository by inject()
    private val locationNetwork: ILocationNetwork by inject()

    override suspend fun getLocation(): Result<LocationModel> {
        val params = HashMap<String, String>()
        params["method"] = "json"
        val location = locationNetwork.getLocation(params)
        when (location) {
            is Result.Success -> location.data.apply {
                return when (code) {
                    locationNetwork.apiSuccessCode -> {
                        //insertion and querying
                        insetAndQuery(this)
                        Result.Success(this)
                    }
                    else -> {
                        val apiException = ApiException(Errors.ApiErrorCodes._1001, null)
                        val appException =
                            AppException(Errors.AppErrorCodes._2001, null).also {
                                it.apiException = apiException
                            }
                        Result.Error(appException)
                    }
                }


            }
            is Result.Error -> {
                val apiException = location.exception
                val appException =
                    AppException(
                        Errors.AppErrorCodes._2002, null
                    ).also {
                        it.apiException = apiException as ApiException
                    }

                return Result.Error(appException)
            }
        }
    }

    private suspend fun insetAndQuery(locationModel: LocationModel) {
        var id = UUID.randomUUID().mostSignificantBits.toInt()
        val isSuccess = locationRepository.insertLocation(locationModel)
        val locationModel =
            locationRepository.findAllLocationsByCountry("Pakistan")
        val locationModelOtherThanPak =
            locationRepository.findAllLocationByIsoCodeAndIpAddress("US", "10.10.10.10")
        val findSingleLocationByCountryName =
            locationRepository.findSingleLocationByCountryName("Pakistan")
    }

}