package eu.tutorials.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.a7minuteworkout.databinding.ActivityBmiBinding
import eu.tutorials.a7minuteworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.historyToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }
        binding?.historyToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}