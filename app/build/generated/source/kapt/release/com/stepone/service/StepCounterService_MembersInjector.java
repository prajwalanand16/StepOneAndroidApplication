package com.stepone.service;

import com.stepone.data.StepDao;
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
public final class StepCounterService_MembersInjector implements MembersInjector<StepCounterService> {
  private final Provider<StepDao> stepDaoProvider;

  public StepCounterService_MembersInjector(Provider<StepDao> stepDaoProvider) {
    this.stepDaoProvider = stepDaoProvider;
  }

  public static MembersInjector<StepCounterService> create(Provider<StepDao> stepDaoProvider) {
    return new StepCounterService_MembersInjector(stepDaoProvider);
  }

  @Override
  public void injectMembers(StepCounterService instance) {
    injectStepDao(instance, stepDaoProvider.get());
  }

  @InjectedFieldSignature("com.stepone.service.StepCounterService.stepDao")
  public static void injectStepDao(StepCounterService instance, StepDao stepDao) {
    instance.stepDao = stepDao;
  }
}
