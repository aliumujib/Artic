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
public final class AuthorMapper_Factory implements Factory<AuthorMapper> {
  @Override
  public AuthorMapper get() {
    return newInstance();
  }

  public static AuthorMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthorMapper newInstance() {
    return new AuthorMapper();
  }

  private static final class InstanceHolder {
    private static final AuthorMapper_Factory INSTANCE = new AuthorMapper_Factory();
  }
}
