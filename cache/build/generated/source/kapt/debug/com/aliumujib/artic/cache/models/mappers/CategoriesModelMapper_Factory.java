package com.aliumujib.artic.cache.models.mappers;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CategoriesModelMapper_Factory implements Factory<CategoriesModelMapper> {
  @Override
  public CategoriesModelMapper get() {
    return newInstance();
  }

  public static CategoriesModelMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CategoriesModelMapper newInstance() {
    return new CategoriesModelMapper();
  }

  private static final class InstanceHolder {
    private static final CategoriesModelMapper_Factory INSTANCE = new CategoriesModelMapper_Factory();
  }
}
