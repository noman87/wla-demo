package com.purevpn.service.location

import com.purevpn.core.errors.Errors
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
    ILocationService, KoinComponent {

    private val locationRepository: ILocationRepository by inject()
    private val locationNetwork: ILocationNetwork by inject()

    override suspend fun getLocation(): Result<LocationModel> {
        val params = HashMap<String, String>()
        params["method"] = "json"
        val location = locationNetwork.getLocation(params)
        if (location is Result.Success) {
            location.data.apply {
                return if (code == locationNetwork.apiSuccessCode) {
                    //insertion and querying
                    var id = UUID.randomUUID().mostSignificantBits.toInt()
                    val isSuccess = locationRepository.insertLocation(this)
                    val locationModel =
                        locationRepository.findAllLocationsByCountry("Pakistan")
                    val locationModelOtherThanPak =
                        locationRepository.findAllLocationByIsoCodeAndIpAddress("US", "10.10.10.10")
                    val findSingleLocationByCountryName = locationRepository.findSingleLocationByCountryName("Pakistan")
                    Result.Success(this)

                } else {
                    val exception =
                        AppException.makeApiException(Errors._1004, Errors.getErrorMessage(Errors._1004), Exception())

                    return Result.Error(exception)
                }


            }
        } else {
            location as Result.Error
            val exception = location.exception
            return Result.Error(location.exception)
        }
    }

}