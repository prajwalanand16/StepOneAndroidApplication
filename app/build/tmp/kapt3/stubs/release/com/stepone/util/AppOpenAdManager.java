package com.stepone.util;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u001a\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0016J\u000e\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/stepone/util/AppOpenAdManager;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "context", "Landroid/content/Context;", "preferenceManager", "Lcom/stepone/util/PreferenceManager;", "(Landroid/content/Context;Lcom/stepone/util/PreferenceManager;)V", "AD_UNIT_ID", "", "appOpenAd", "Lcom/google/android/gms/ads/appopen/AppOpenAd;", "currentActivity", "Landroid/app/Activity;", "isLoadingAd", "", "openCount", "", "isAdAvailable", "loadAd", "", "onActivityCreated", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "registerActivityLifecycleCallbacks", "application", "Landroid/app/Application;", "showAdIfAvailable", "app_release"})
public final class AppOpenAdManager implements android.app.Application.ActivityLifecycleCallbacks, androidx.lifecycle.DefaultLifecycleObserver {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.stepone.util.PreferenceManager preferenceManager = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.ads.appopen.AppOpenAd appOpenAd;
    private boolean isLoadingAd = false;
    @org.jetbrains.annotations.Nullable()
    private android.app.Activity currentActivity;
    private int openCount = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String AD_UNIT_ID = "ca-app-pub-7818602725323272/7845263246";
    
    @javax.inject.Inject()
    public AppOpenAdManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.stepone.util.PreferenceManager preferenceManager) {
        super();
    }
    
    public final void registerActivityLifecycleCallbacks(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
    }
    
    private final void loadAd() {
    }
    
    private final boolean isAdAvailable() {
        return false;
    }
    
    private final void showAdIfAvailable(android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onStart(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleOwner owner) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityStarted(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivityResumed(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivityPaused(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivityStopped(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @java.lang.Override()
    public void onActivitySaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onActivityDestroyed(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
}