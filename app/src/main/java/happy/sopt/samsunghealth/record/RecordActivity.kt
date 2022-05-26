package happy.sopt.samsunghealth.record

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.widget.doOnTextChanged
import happy.sopt.samsunghealth.databinding.ActivityRecordBinding
import happy.sopt.samsunghealth.main.home.HomeFragment
import happy.sopt.samsunghealth.main.home.HomeFragment.Companion.WEIGHT_LEFT
import happy.sopt.samsunghealth.main.home.HomeFragment.Companion.WEIGHT_RIGHT

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //취소 버튼 눌렀을 떄 홈으로 가기
        setOnCancelClickEvent()

        //저장 버튼 누르면 홈 화면 돌아가기
        setOnSaveClickEvent()

        initWeightLogic()
        initWeightEndLogic()
    }

    private fun setOnSaveClickEvent() {
        binding.btnSave.setOnClickListener {
            val saveIntent = Intent(this, HomeFragment::class.java)
            saveIntent.putExtra(WEIGHT_LEFT, binding.etInputWeightLeft.text.toString())
            saveIntent.putExtra(WEIGHT_RIGHT, binding.etInputWeightRight.text.toString())
            setResult(RESULT_OK, saveIntent)
            /*번들을 사용한다면 아래처럼
            saveIntent.putExtra("weight", bundleOf(
                WEIGHT_LEFT to binding.etInputWeightLeft.text.toString(),
                WEIGHT_RIGHT to binding.etInputWeightRight.text.toString()
            ))*/
            finish()
        }
    }

    private fun setOnCancelClickEvent() {
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun initWeightLogic() = binding.etInputWeightLeft.run {
        inputType = InputType.TYPE_NUMBER_FLAG_SIGNED or InputType.TYPE_CLASS_NUMBER

        // 포커스된 상태에 따라서 위 아래 텍스트가 보일지 말지를 결정
        setOnFocusChangeListener { _, isFocused ->
            with(binding) {
                tvInputTop.isInvisible = isFocused
                tvInputTopEnd.isInvisible = isFocused
                tvInputBottom.isInvisible = isFocused
                tvInputBottomEnd.isInvisible = isFocused
                dotBottom.isInvisible = isFocused
                dotTop.isInvisible = isFocused
            }
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
            with(binding) {
                tvInputTop.isInvisible = isFocused
                tvInputTopEnd.isInvisible = isFocused
                tvInputBottom.isInvisible = isFocused
                tvInputBottomEnd.isInvisible = isFocused
                dotBottom.isInvisible = isFocused
                dotTop.isInvisible = isFocused
            }
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