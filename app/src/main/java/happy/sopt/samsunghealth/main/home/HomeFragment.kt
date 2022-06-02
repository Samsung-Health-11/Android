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
import happy.sopt.samsunghealth.api.HealthService.EditWaterRequest
import happy.sopt.samsunghealth.api.ServiceFactory
import happy.sopt.samsunghealth.api.parseResponse
import happy.sopt.samsunghealth.databinding.FragmentHomeBinding
import happy.sopt.samsunghealth.model.WaterRecordType.MINUS
import happy.sopt.samsunghealth.model.WaterRecordType.PLUS
import happy.sopt.samsunghealth.record.RecordActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var water = 0
    private var weight = 0.0

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
        setWaterUpdateClickListener()
    }

    private fun setOnInputWeightClickEvent() {
        binding.btInputWeight.setOnClickListener {
            val intent = Intent(context, RecordActivity::class.java)
            resultLauncher.launch(intent)
            intent.putExtra("setWeightLeft", binding.countKg.text.split(".")[0])
            intent.putExtra("setWeightRight", binding.countKg.text.split(".")[1])
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
                binding.tvFoodCount.text = calorie.intake.toString()
                binding.tvFoodAllcount.text = calorie.target.toString()
            }
            binding.countKg.text = data.weight.toString()
            binding.tvDrinkwater.text = data.water.toString()

            water = data.water
            weight = data.weight
        })
    }

    private fun setWaterUpdateClickListener() {
        binding.ivWaterMinusbuttonOff.setOnClickListener {
            parseResponse(ServiceFactory.healthService.editWater(EditWaterRequest(MINUS)), {
                water--
                binding.tvDrinkwater.text = water.toString()
            })
        }
        binding.ivWaterPlusbuttonOn.setOnClickListener {
            parseResponse(ServiceFactory.healthService.editWater(EditWaterRequest(PLUS)), {
                water++
                binding.tvDrinkwater.text = water.toString()
            })
        }
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
