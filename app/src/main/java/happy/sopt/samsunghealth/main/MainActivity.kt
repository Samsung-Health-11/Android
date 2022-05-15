package happy.sopt.samsunghealth.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import happy.sopt.samsunghealth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPager()
    }

    private fun initPager() {
        binding.pager.apply {
            adapter = MainPagerAdapter(this@MainActivity)
        }
    }
}