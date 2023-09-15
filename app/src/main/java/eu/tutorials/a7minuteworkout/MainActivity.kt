package eu.tutorials.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import eu.tutorials.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // setting variables using root-binding
        binding?.startBtn?.setOnClickListener {
            Toast.makeText(this@MainActivity, "Here we go!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}