package com.aliumujib.artic.remote.mapper;

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
public final class PostsMapper_Factory implements Factory<PostsMapper> {
  private final Provider<CategoryMapper> categoryMapperProvider;

  private final Provider<AuthorMapper> authorMapperProvider;

  public PostsMapper_Factory(Provider<CategoryMapper> categoryMapperProvider,
      Provider<AuthorMapper> authorMapperProvider) {
    this.categoryMapperProvider = categoryMapperProvider;
    this.authorMapperProvider = authorMapperProvider;
  }

  @Override
  public PostsMapper get() {
    return newInstance(categoryMapperProvider.get(), authorMapperProvider.get());
  }

  public static PostsMapper_Factory create(Provider<CategoryMapper> categoryMapperProvider,
      Provider<AuthorMapper> authorMapperProvider) {
    return new PostsMapper_Factory(categoryMapperProvider, authorMapperProvider);
  }

  public static PostsMapper newInstance(CategoryMapper categoryMapper, AuthorMapper authorMapper) {
    return new PostsMapper(categoryMapper, authorMapper);
  }
}
