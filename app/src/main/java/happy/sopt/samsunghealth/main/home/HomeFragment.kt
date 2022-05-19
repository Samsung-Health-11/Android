package happy.sopt.samsunghealth.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import happy.sopt.samsunghealth.R
import happy.sopt.samsunghealth.databinding.FragmentHomeBinding
import happy.sopt.samsunghealth.record.RecordActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentHomeBinding.inflate(inflater, container, false).let {
        _binding = it
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clickEvent()
    }

    private fun clickEvent() {
        binding.btInputweight.setOnClickListener {
            val intent = Intent(context, RecordActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


/*
FragmentHomeBinding.inflate(inflater, container, false).let {
            _binding = it
            binding.root
            clickEvent()
        }
 */