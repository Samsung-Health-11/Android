package happy.sopt.samsunghealth.api

data class BaseResponse<T>(
    val status: Int, val success: Boolean, val message: String, val data: T?
)