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
public final class CommentMapper_Factory implements Factory<CommentMapper> {
  @Override
  public CommentMapper get() {
    return newInstance();
  }

  public static CommentMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CommentMapper newInstance() {
    return new CommentMapper();
  }

  private static final class InstanceHolder {
    private static final CommentMapper_Factory INSTANCE = new CommentMapper_Factory();
  }
}
