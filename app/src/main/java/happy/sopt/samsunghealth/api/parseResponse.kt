package happy.sopt.samsunghealth.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> parseResponse(
    call: Call<BaseResponse<T>>, onSuccess: (data: T) -> Unit = {}, onFailure: (message: String) -> Unit = {}
) {
    call.enqueue(object : Callback<BaseResponse<T>> {
        override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
            if (response.body()?.success == true && response.body()?.data != null) {
                onSuccess(response.body()!!.data!!)
            } else {
                onFailure(response.body()?.message ?: "")
            }
        }

        override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
            onFailure(t.message ?: "")
        }
    })
}