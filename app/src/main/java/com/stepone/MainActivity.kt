package com.stepone

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.stepone.service.StepCounterService
import com.stepone.ui.screens.DashboardScreen
import com.stepone.ui.theme.StepOneTheme
import com.stepone.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultListener {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Checkout.preload(applicationContext)
        requestPermissions()
        startStepService()

        setContent {
            StepOneTheme {
                val stepGoal = remember { mutableStateOf(preferenceManager.getStepGoal()) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardScreen(
                        isPro = preferenceManager.isPro(),
                        stepGoal = stepGoal.value,
                        onRemoveAdsClicked = { startPayment() },
                        onStepGoalChanged = { newGoal ->
                            preferenceManager.setStepGoal(newGoal)
                            stepGoal.value = newGoal
                        }
                    )
                }
            }
        }
    }

    private fun startPayment() {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_Siqi22Ym2wBnNK") // TODO: Replace with your actual key

        try {
            val options = JSONObject()
            options.put("name", "StepOne Pro")
            options.put("description", "Remove Ads & Support Development")
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", "9900") // Amount in paise (9900 = ₹99)
            
            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            checkout.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        Toast.makeText(this, "Payment Successful: $razorpayPaymentId", Toast.LENGTH_LONG).show()
        preferenceManager.setPro(true)
    }

    override fun onPaymentError(code: Int, response: String?) {
        if (code == Checkout.PAYMENT_CANCELED) {
            Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Payment Failed: $response", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestPermissions() {
        val permissions = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.ACTIVITY_RECOGNITION)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        if (permissions.isNotEmpty()) {
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}.launch(
                permissions.toTypedArray()
            )
        }
    }

    private fun startStepService() {
        val intent = Intent(this, StepCounterService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}
