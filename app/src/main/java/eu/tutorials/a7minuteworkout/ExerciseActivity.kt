package eu.tutorials.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import eu.tutorials.a7minuteworkout.databinding.ActivityExerciseBinding
import eu.tutorials.a7minuteworkout.databinding.ActivityMainBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<Exercise>? = null
    private var currExercisePos = -1

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        // assign list of exercises
        exerciseList = Constants.defaultExerciseList()

        // text to speech
        tts = TextToSpeech(this, this)

        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        setUpRestView()
    }

    private fun setUpRestView() {
        binding?.flRestProgress?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseProgress?.visibility = View.INVISIBLE
        binding?.exerciseImage?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.tvUpcomingExerciseName?.text = exerciseList!![currExercisePos + 1].getName()
        setRestProgressBar()
    }

    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress
        restTimer = object: CountDownTimer(10000,1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }
            override fun onFinish() {
                currExercisePos++
                setUpExerciseView()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        // shut down text to speech when activity is destroyed
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        binding = null
    }

    private fun setUpExerciseView() {
        binding?.flRestProgress?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseProgress?.visibility = View.VISIBLE
        binding?.exerciseImage?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        speakOut(exerciseList!![currExercisePos].getName())

        binding?.exerciseImage?.setImageResource(exerciseList!![currExercisePos].getIMG())
        binding?.tvExerciseName?.text = exerciseList!![currExercisePos].getName()
        setExerciseProgressBar()
    }

    private fun setExerciseProgressBar() {
        binding?.exerciseProgress?.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(30000,1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.exerciseProgress?.progress = 30 - exerciseProgress
                binding?.tvExerciseTimer?.text = (30 - exerciseProgress).toString()
            }
            override fun onFinish() {
                if (currExercisePos < exerciseList?.size!! -1)
                    setUpRestView()
                else
                    Toast.makeText(this@ExerciseActivity, "Congratulations! You've completed the 7 minute workout!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.e("TTS", "The language specified is not supported...")
            else
                Log.e("TTS", "Initialization failed...")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

}