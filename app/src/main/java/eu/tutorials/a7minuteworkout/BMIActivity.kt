package eu.tutorials.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import eu.tutorials.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val CUSTOMARY_UNITS_VIEW = "CUSTOMARY_UNITS_VIEW"
    }

    private var binding: ActivityBmiBinding? = null
    private var currVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.bmiToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.bmiToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        toVisibleMetricUnitsView()
        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedID: Int ->
            if (checkedID == R.id.metricUnits) {
                toVisibleMetricUnitsView()
            } else {
                toVisibleCustomaryUnitsView()
            }
        }

        // calculate BMI upon calculate btn being selected
        binding?.btnCalculateBMI?.setOnClickListener{
            calculateUnits()
        }


    }

    private fun toVisibleMetricUnitsView() {
        // set current view
        currVisibleView = METRIC_UNITS_VIEW
        // set metric units visible
        binding?.metricWeight?.visibility = View.VISIBLE
        binding?.metricHeight?.visibility = View.VISIBLE
        // set customary units as invisible
        binding?.customaryWeight?.visibility = View.INVISIBLE
        binding?.customaryHeightFeet?.visibility = View.INVISIBLE
        binding?.customaryHeightInches?.visibility = View.INVISIBLE
        // clear potentially old assigned values
        binding?.etMetricHeight?.text!!.clear()
        binding?.etMetricWeight?.text!!.clear()
        // set bmi LL as invisible
        binding?.displayBMIResult?.visibility = View.INVISIBLE
    }

    private fun toVisibleCustomaryUnitsView() {
        // set current view
        currVisibleView = CUSTOMARY_UNITS_VIEW
        // set customary units visible
        binding?.customaryWeight?.visibility = View.VISIBLE
        binding?.customaryHeightFeet?.visibility = View.VISIBLE
        binding?.customaryHeightInches?.visibility = View.VISIBLE
        // set metric units as invisible
        binding?.metricWeight?.visibility = View.INVISIBLE
        binding?.metricHeight?.visibility = View.INVISIBLE
        // clear potentially old assigned values
        binding?.etCustomaryWeight?.text!!.clear()
        binding?.etCustomaryHeightFeet?.text!!.clear()
        binding?.etCustomaryHeightInches?.text!!.clear()
        // set bmi LL as invisible
        binding?.displayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDesc: String
        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very Severely Underweight"
            bmiDesc = "Uh oh! You REALLY need to take better care of yourself! Eat more food..."
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely Underweight"
            bmiDesc = "Oops! You should try and take better care of yourself! Try to focus on your diet and eating three meals a day..."
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDesc = "Hm! You should take better care of yourself! Try to focus on and eating around three meals a day..."
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDesc = "Congratulations! Seems like you are in great shape... Keep doing what you're doing!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDesc = "Oh no! You should take a little better care of yourself! Try to work out a few times a week..."
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Moderately Obese"
            bmiDesc = "Hm! You should take better care of yourself! Try to work out more..."
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Severely Obese"
            bmiDesc = "Uh oh! You REALLY need take better care of yourself! Work out more..."
        } else {
            bmiLabel = "VERY Severely Obese"
            bmiDesc = "OMG! You are REALLY in a dangerous condition! GET HELP..."
        }
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.displayBMIResult?.visibility = View.VISIBLE
        binding?.BMIValue?.text = bmiValue
        binding?.weightResult?.text = bmiLabel
        binding?.adviceResult?.text = bmiDesc
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        if (binding?.etMetricWeight?.text.toString().isEmpty()) {
            isValid = false
        }
        else if (binding?.etMetricHeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun calculateUnits() {
        if (currVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val height: Float = binding?.etMetricHeight?.text.toString().toFloat() / 100
                val weight: Float = binding?.etMetricWeight?.text.toString().toFloat()
                val bmi = weight / (height * height)
                displayBMIResult(bmi)
            }
            else
                Toast.makeText(this@BMIActivity, "Please re-enter valid values...", Toast.LENGTH_SHORT).show()
        } else {
            if (validateCustomaryUnits()) {
                val customaryWeight: Float = binding?.etCustomaryWeight?.text.toString().toFloat()
                val customaryHeightFeet: String = binding?.etCustomaryHeightFeet?.text.toString()
                val customaryHeightInches: String = binding?.etCustomaryHeightInches?.text.toString()
                val heightValue = customaryHeightInches.toFloat() + ( customaryHeightFeet.toFloat() * 12 )
                val bmi = 703 * ( customaryWeight / (heightValue * heightValue) )
                displayBMIResult(bmi)
            }
            else
                Toast.makeText(this@BMIActivity, "Please re-enter valid values...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateCustomaryUnits(): Boolean {
        var isValid = true
        if (binding?.etCustomaryWeight?.text.toString().isEmpty()) {
            isValid = false
        }
        else if (binding?.etCustomaryHeightFeet?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.etCustomaryHeightInches?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

}