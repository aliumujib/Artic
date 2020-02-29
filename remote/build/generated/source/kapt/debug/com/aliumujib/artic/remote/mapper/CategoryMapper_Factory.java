package com.aliumujib.artic.remote.mapper;

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
public final class CategoryMapper_Factory implements Factory<CategoryMapper> {
  @Override
  public CategoryMapper get() {
    return newInstance();
  }

  public static CategoryMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CategoryMapper newInstance() {
    return new CategoryMapper();
  }

  private static final class InstanceHolder {
    private static final CategoryMapper_Factory INSTANCE = new CategoryMapper_Factory();
  }
}
