package com.stepone.service;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 ,2\u00020\u00012\u00020\u0002:\u0001,B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u001a\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001d\u001a\u00020\u0007H\u0016J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0018H\u0016J\b\u0010#\u001a\u00020\u0018H\u0016J\u0012\u0010$\u001a\u00020\u00182\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\"\u0010\'\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u0007H\u0016J\b\u0010*\u001a\u00020\u0018H\u0002J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/stepone/service/StepCounterService;", "Landroid/app/Service;", "Landroid/hardware/SensorEventListener;", "()V", "currentDay", "", "initialStepsAtBoot", "", "sensorManager", "Landroid/hardware/SensorManager;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "stepDao", "Lcom/stepone/data/StepDao;", "getStepDao", "()Lcom/stepone/data/StepDao;", "setStepDao", "(Lcom/stepone/data/StepDao;)V", "stepSensor", "Landroid/hardware/Sensor;", "createNotification", "Landroid/app/Notification;", "steps", "createNotificationChannel", "", "getCurrentDate", "loadInitialSteps", "onAccuracyChanged", "sensor", "accuracy", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "onStartCommand", "flags", "startId", "persistSteps", "updateNotification", "Companion", "app_debug"})
public final class StepCounterService extends android.app.Service implements android.hardware.SensorEventListener {
    @javax.inject.Inject()
    public com.stepone.data.StepDao stepDao;
    private android.hardware.SensorManager sensorManager;
    @org.jetbrains.annotations.Nullable()
    private android.hardware.Sensor stepSensor;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private int initialStepsAtBoot = -1;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentDay;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> stepsFlow = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.stepone.service.StepCounterService.Companion Companion = null;
    
    public StepCounterService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.stepone.data.StepDao getStepDao() {
        return null;
    }
    
    public final void setStepDao(@org.jetbrains.annotations.NotNull()
    com.stepone.data.StepDao p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void loadInitialSteps() {
    }
    
    private final java.lang.String getCurrentDate() {
        return null;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void persistSteps() {
    }
    
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor sensor, int accuracy) {
    }
    
    private final android.app.Notification createNotification(int steps) {
        return null;
    }
    
    private final void updateNotification(int steps) {
    }
    
    private final void createNotificationChannel() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\r"}, d2 = {"Lcom/stepone/service/StepCounterService$Companion;", "", "()V", "stepsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "getStepsFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "addSteps", "", "context", "Landroid/content/Context;", "count", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> getStepsFlow() {
            return null;
        }
        
        public final void addSteps(@org.jetbrains.annotations.NotNull()
        android.content.Context context, int count) {
        }
    }
}