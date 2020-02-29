package com.aliumujib.artic.cache.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CacheTimeManager_Factory implements Factory<CacheTimeManager> {
  private final Provider<Context> contextProvider;

  public CacheTimeManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CacheTimeManager get() {
    return newInstance(contextProvider.get());
  }

  public static CacheTimeManager_Factory create(Provider<Context> contextProvider) {
    return new CacheTimeManager_Factory(contextProvider);
  }

  public static CacheTimeManager newInstance(Context context) {
    return new CacheTimeManager(context);
  }
}
