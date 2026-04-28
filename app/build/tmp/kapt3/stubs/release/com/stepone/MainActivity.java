package com.stepone;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u001a\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0002J\b\u0010\u0016\u001a\u00020\u000bH\u0002J\b\u0010\u0017\u001a\u00020\u000bH\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/stepone/MainActivity;", "Landroidx/activity/ComponentActivity;", "Lcom/razorpay/PaymentResultListener;", "()V", "preferenceManager", "Lcom/stepone/util/PreferenceManager;", "getPreferenceManager", "()Lcom/stepone/util/PreferenceManager;", "setPreferenceManager", "(Lcom/stepone/util/PreferenceManager;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPaymentError", "code", "", "response", "", "onPaymentSuccess", "razorpayPaymentId", "requestPermissions", "startPayment", "startStepService", "app_release"})
public final class MainActivity extends androidx.activity.ComponentActivity implements com.razorpay.PaymentResultListener {
    @javax.inject.Inject()
    public com.stepone.util.PreferenceManager preferenceManager;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.stepone.util.PreferenceManager getPreferenceManager() {
        return null;
    }
    
    public final void setPreferenceManager(@org.jetbrains.annotations.NotNull()
    com.stepone.util.PreferenceManager p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void startPayment() {
    }
    
    @java.lang.Override()
    public void onPaymentSuccess(@org.jetbrains.annotations.Nullable()
    java.lang.String razorpayPaymentId) {
    }
    
    @java.lang.Override()
    public void onPaymentError(int code, @org.jetbrains.annotations.Nullable()
    java.lang.String response) {
    }
    
    private final void requestPermissions() {
    }
    
    private final void startStepService() {
    }
}