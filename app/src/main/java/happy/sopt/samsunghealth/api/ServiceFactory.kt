package happy.sopt.samsunghealth.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://43.200.4.231:8000/").build()

    private fun <T> createService(serviceClass: Class<T>) = retrofit.create(serviceClass)

    val healthService = createService(HealthService::class.java)
}