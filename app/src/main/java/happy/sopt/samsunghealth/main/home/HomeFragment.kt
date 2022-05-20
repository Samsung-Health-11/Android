package happy.sopt.samsunghealth.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import happy.sopt.samsunghealth.databinding.ActivityRecordBinding
import happy.sopt.samsunghealth.databinding.FragmentHomeBinding
import happy.sopt.samsunghealth.record.RecordActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) =
        FragmentHomeBinding.inflate(inflater, container, false).let {
            _binding = it
            binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //view가 만들어지고 난 후이며 이떄부터 Fragment가 activity에 온전히 접근 가능

        setOnInputWeightClickEvent()
    }

    private fun setOnInputWeightClickEvent() {
        binding.btInputWeight.setOnClickListener {
            val intent = Intent(context, RecordActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
