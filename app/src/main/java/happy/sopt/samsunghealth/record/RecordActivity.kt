package happy.sopt.samsunghealth.record

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.widget.doOnTextChanged
import happy.sopt.samsunghealth.databinding.ActivityRecordBinding
import happy.sopt.samsunghealth.main.home.HomeFragment

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //취소 버튼 눌렀을 떄 홈으로 가기
        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }

        //저장 버튼 누르면 입력값이 홈 화면에 뜨게 해주기
        binding.btnSave.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            intent.putExtra("inputWeight", binding.etInputWeightLeft.text.toString() + '.' + binding.etInputWeightRight.text.toString())
            startActivity(intent)
        }


        initWeightLogic()
        initWeightEndLogic()
    }

    private fun initWeightLogic() = binding.etInputWeightLeft.run {
        inputType = InputType.TYPE_NUMBER_FLAG_SIGNED or InputType.TYPE_CLASS_NUMBER

        // 포커스된 상태에 따라서 위 아래 텍스트가 보일지 말지를 결정
        setOnFocusChangeListener { _, isFocused ->
            binding.tvInputTop.isInvisible = isFocused
            binding.tvInputTopEnd.isInvisible = isFocused
            binding.tvInputBottom.isInvisible = isFocused
            binding.tvInputBottomEnd.isInvisible = isFocused
            binding.dotBottom.isInvisible = isFocused
            binding.dotTop.isInvisible = isFocused
        }

        // 텍스트가 변했을 때 위 아래 텍스트들을 정수값을 파싱해서 변경
        doOnTextChanged { text, _, _, _ ->
            text!!.toString().toIntOrNull()?.let(::adjustLeftTopAndBottomHintTexts)
        } // 가장 처음의 정수값을 반영
        text!!.toString().toIntOrNull()?.let(::adjustLeftTopAndBottomHintTexts)
    }

    private fun initWeightEndLogic() = binding.etInputWeightRight.run {
        inputType = InputType.TYPE_NUMBER_FLAG_SIGNED or InputType.TYPE_CLASS_NUMBER

        setOnFocusChangeListener { _, isFocused ->
            binding.tvInputTop.isInvisible = isFocused
            binding.tvInputTopEnd.isInvisible = isFocused
            binding.tvInputBottom.isInvisible = isFocused
            binding.tvInputBottomEnd.isInvisible = isFocused
            binding.dotBottom.isInvisible = isFocused
            binding.dotTop.isInvisible = isFocused
        }

        doOnTextChanged { text, _, _, _ ->
            text!!.toString().toIntOrNull()?.let(::adjustRightTopAndBottomHintTexts)
        }
        text!!.toString().toIntOrNull()?.let(::adjustRightTopAndBottomHintTexts)
    }

    private fun adjustLeftTopAndBottomHintTexts(number: Int) {
        binding.tvInputTop.text = (number - 1).toString()
        binding.tvInputBottom.text = (number + 1).toString()
    }

    private fun adjustRightTopAndBottomHintTexts(number: Int) {
        binding.tvInputTopEnd.text = (number - 1).toString()
        binding.tvInputBottomEnd.text = (number + 1).toString()
    }
}