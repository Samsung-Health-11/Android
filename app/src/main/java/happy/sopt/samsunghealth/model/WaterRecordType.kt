package happy.sopt.samsunghealth.model

//@JsonAdapter()
enum class WaterRecordType(val value: String) {
    PLUS("plus"), MINUS("minus")
}

//class WaterRecordTypeSerializer: DeSeri