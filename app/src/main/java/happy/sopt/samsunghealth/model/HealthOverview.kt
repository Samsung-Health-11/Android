package happy.sopt.samsunghealth.model

import com.google.gson.annotations.SerializedName

data class HealthOverview(
    @SerializedName("calorie") val calorie: Calorie,
    @SerializedName("sleep") val sleep: Sleep,
    @SerializedName("step") val step: Step,
    @SerializedName("water") val water: Int,
    @SerializedName("weight") val weight: Double
) {
    data class Calorie(
        @SerializedName("intake") val intake: Int, @SerializedName("target") val target: Int
    )

    data class Sleep(
        @SerializedName("hour") val hour: Int,
        @SerializedName("minute") val minute: Int,
        @SerializedName("time") val time: String
    )

    data class Step(
        @SerializedName("activity") val activity: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("percent") val percent: Int,
        @SerializedName("target") val target: Int,
        @SerializedName("time") val time: Int
    )
}