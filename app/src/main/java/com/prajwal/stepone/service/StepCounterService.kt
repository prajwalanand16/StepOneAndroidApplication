package com.prajwal.stepone.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.prajwal.stepone.MainActivity
import com.prajwal.stepone.R
import com.prajwal.stepone.data.StepDao
import com.prajwal.stepone.data.StepEntry
import com.prajwal.stepone.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@AndroidEntryPoint
class StepCounterService : Service(), SensorEventListener {

    @Inject
    lateinit var stepDao: StepDao

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private var initialStepsAtBoot = -1
    private var currentDay = getCurrentDate()

    companion object {
        val stepsFlow = MutableStateFlow(0)

        fun addSteps(context: Context, count: Int) {
            val intent = Intent(context, StepCounterService::class.java).apply {
                action = "ACTION_ADD_STEPS"
                putExtra("EXTRA_COUNT", count)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                Constants.NOTIFICATION_ID,
                createNotification(0),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_HEALTH
            )
        } else {
            startForeground(Constants.NOTIFICATION_ID, createNotification(0))
        }
        loadInitialSteps()
    }

    private fun loadInitialSteps() {
        serviceScope.launch {
            stepDao.getStepsForDate(currentDay).collect { entry ->
                stepsFlow.value = entry?.steps ?: 0
                updateNotification(stepsFlow.value)
            }
        }
    }

    private fun getCurrentDate(): String {
        return java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == "ACTION_ADD_STEPS") {
            val count = intent.getIntExtra("EXTRA_COUNT", 0)
            stepsFlow.value += count
            persistSteps()
            updateNotification(stepsFlow.value)
        }
        return START_STICKY
    }

    private fun persistSteps() {
        serviceScope.launch {
            stepDao.insertOrUpdate(StepEntry(currentDay, stepsFlow.value))
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val totalStepsSinceBoot = event.values[0].toInt()

            if (initialStepsAtBoot == -1) {
                initialStepsAtBoot = totalStepsSinceBoot
                return
            }

            if (totalStepsSinceBoot < initialStepsAtBoot) {
                // System rebooted
                initialStepsAtBoot = totalStepsSinceBoot
                return
            }

            val delta = totalStepsSinceBoot - initialStepsAtBoot
            if (delta > 0) {
                initialStepsAtBoot = totalStepsSinceBoot

                val today = getCurrentDate()
                if (today != currentDay) {
                    currentDay = today
                    stepsFlow.value = 0
                }

                stepsFlow.value += delta
                persistSteps()
                updateNotification(stepsFlow.value)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun createNotification(steps: Int): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setContentTitle("StepOne is counting")
            .setContentText("$steps steps today")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification(steps: Int) {
        val notification = createNotification(steps)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(Constants.NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.CHANNEL_ID,
                "Step Counter Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}
