package happy.sopt.samsunghealth.record

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.widget.doOnTextChanged
import happy.sopt.samsunghealth.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initWeightLogic()
    }

    private fun initWeightLogic() = binding.etInputWeightLeft.run {
        inputType = InputType.TYPE_NUMBER_FLAG_SIGNED or InputType.TYPE_CLASS_NUMBER

        // 포커스된 상태에 따라서 위 아래 텍스트가 보일지 말지를 결정
        setOnFocusChangeListener { _, isFocused ->
            binding.tvInputTop.isInvisible = isFocused
            binding.tvInputBottom.isInvisible = isFocused
        }

        // 텍스트가 변했을 때 위 아래 텍스트들을 정수값을 파싱해서 변경
        doOnTextChanged { text, _, _, _ ->
            text!!.toString().toIntOrNull()?.let(::adjustLeftTopAndBottomHintTexts)
        } // 가장 처음의 정수값을 반영
        text!!.toString().toIntOrNull()?.let(::adjustLeftTopAndBottomHintTexts)
    }

    private fun adjustLeftTopAndBottomHintTexts(number: Int) {
        binding.tvInputTop.text = (number - 1).toString()
        binding.tvInputBottom.text = (number + 1).toString()
    }
}