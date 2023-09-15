package eu.tutorials.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.a7minuteworkout.databinding.ActivityExerciseBinding
import eu.tutorials.a7minuteworkout.databinding.ActivityMainBinding

class ExerciseActivity : AppCompatActivity() {

    private var binding: ActivityExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}