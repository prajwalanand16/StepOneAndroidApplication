package com.stepone;

import com.stepone.util.AppOpenAdManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
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
public final class StepOneApp_MembersInjector implements MembersInjector<StepOneApp> {
  private final Provider<AppOpenAdManager> appOpenAdManagerProvider;

  public StepOneApp_MembersInjector(Provider<AppOpenAdManager> appOpenAdManagerProvider) {
    this.appOpenAdManagerProvider = appOpenAdManagerProvider;
  }

  public static MembersInjector<StepOneApp> create(
      Provider<AppOpenAdManager> appOpenAdManagerProvider) {
    return new StepOneApp_MembersInjector(appOpenAdManagerProvider);
  }

  @Override
  public void injectMembers(StepOneApp instance) {
    injectAppOpenAdManager(instance, appOpenAdManagerProvider.get());
  }

  @InjectedFieldSignature("com.stepone.StepOneApp.appOpenAdManager")
  public static void injectAppOpenAdManager(StepOneApp instance,
      AppOpenAdManager appOpenAdManager) {
    instance.appOpenAdManager = appOpenAdManager;
  }
}
