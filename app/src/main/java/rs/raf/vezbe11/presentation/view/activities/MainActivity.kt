package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.ActivityMainBinding
import rs.raf.vezbe11.presentation.view.adapters.MainPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        binding.viewPager.adapter =
            MainPagerAdapter(
                supportFragmentManager,
                this
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}
