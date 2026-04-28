package com.stepone.di;

import com.stepone.data.StepDao;
import com.stepone.data.StepDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class DataModule_ProvideStepDaoFactory implements Factory<StepDao> {
  private final Provider<StepDatabase> databaseProvider;

  public DataModule_ProvideStepDaoFactory(Provider<StepDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public StepDao get() {
    return provideStepDao(databaseProvider.get());
  }

  public static DataModule_ProvideStepDaoFactory create(Provider<StepDatabase> databaseProvider) {
    return new DataModule_ProvideStepDaoFactory(databaseProvider);
  }

  public static StepDao provideStepDao(StepDatabase database) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideStepDao(database));
  }
}
