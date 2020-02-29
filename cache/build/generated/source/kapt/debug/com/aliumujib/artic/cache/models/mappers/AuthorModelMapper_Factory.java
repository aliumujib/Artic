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
public final class AuthorModelMapper_Factory implements Factory<AuthorModelMapper> {
  @Override
  public AuthorModelMapper get() {
    return newInstance();
  }

  public static AuthorModelMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthorModelMapper newInstance() {
    return new AuthorModelMapper();
  }

  private static final class InstanceHolder {
    private static final AuthorModelMapper_Factory INSTANCE = new AuthorModelMapper_Factory();
  }
}
