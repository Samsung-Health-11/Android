package happy.sopt.samsunghealth.api

import happy.sopt.samsunghealth.model.HealthOverview
import happy.sopt.samsunghealth.model.WaterRecordType
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface HealthService {
    @GET("health")
    fun getHealth(): Call<BaseResponse<HealthOverview>>

    @POST("health/weight")
    fun recordWeight(
        @Body request: RecordWeightRequest
    ): Call<BaseResponse<Nothing>>

    data class RecordWeightRequest(val weight: Double)

    @PUT("health/water")
    fun editWater(
        @Body request: EditWaterRequest
    ): Call<BaseResponse<EditWaterResponse>>

    data class EditWaterRequest(val type: WaterRecordType)
    data class EditWaterResponse(val water: Double)
}