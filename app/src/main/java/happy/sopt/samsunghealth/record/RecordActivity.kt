package happy.sopt.samsunghealth.record

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import happy.sopt.samsunghealth.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}