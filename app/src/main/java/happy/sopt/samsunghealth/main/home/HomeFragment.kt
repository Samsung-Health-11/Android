package happy.sopt.samsunghealth.main.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import happy.sopt.samsunghealth.api.ServiceFactory
import happy.sopt.samsunghealth.api.parseResponse
import happy.sopt.samsunghealth.databinding.FragmentHomeBinding
import happy.sopt.samsunghealth.record.RecordActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false).let {
        _binding = it
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnInputWeightClickEvent()
        setWeightResult()
        fetchDatas()
    }

    private fun setOnInputWeightClickEvent() {
        binding.btInputWeight.setOnClickListener {
            val intent = Intent(context, RecordActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setWeightResult() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != RESULT_OK) return@registerForActivityResult
            val data = result.data ?: return@registerForActivityResult
            binding.countKg.text = "${data.getStringExtra(WEIGHT_LEFT)}.${data.getStringExtra(WEIGHT_RIGHT)}"
        }
    }

    private fun fetchDatas() {
        parseResponse(ServiceFactory.healthService.getHealth(), { data ->
            data.step.let { step ->
                binding.tvWalkCount.text = step.count.toString()
                binding.tvWalkAllCount.text = step.target.toString()
                binding.tvShoesCount.text = step.count.toString()
                binding.tvClockcount.text = step.time.toString()
                binding.tvFire.text = step.activity.toString()
            }
            data.calorie.let { calorie ->
                binding.foodCount.text = calorie.intake.toString()
                binding.foodAllcount.text = calorie.target.toString()
            }
            binding.countKg.text = data.weight.toString()
            binding.tvDrinkwater.text = "${data.water}잔"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val WEIGHT_LEFT = "weightLeft"
        const val WEIGHT_RIGHT = "weightRight"
    }

}
