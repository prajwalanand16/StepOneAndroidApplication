package com.stepone.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppOpenAdManager_Factory implements Factory<AppOpenAdManager> {
  private final Provider<Context> contextProvider;

  private final Provider<PreferenceManager> preferenceManagerProvider;

  public AppOpenAdManager_Factory(Provider<Context> contextProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    this.contextProvider = contextProvider;
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  @Override
  public AppOpenAdManager get() {
    return newInstance(contextProvider.get(), preferenceManagerProvider.get());
  }

  public static AppOpenAdManager_Factory create(Provider<Context> contextProvider,
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new AppOpenAdManager_Factory(contextProvider, preferenceManagerProvider);
  }

  public static AppOpenAdManager newInstance(Context context, PreferenceManager preferenceManager) {
    return new AppOpenAdManager(context, preferenceManager);
  }
}
