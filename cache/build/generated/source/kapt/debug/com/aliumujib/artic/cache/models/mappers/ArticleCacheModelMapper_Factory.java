package com.aliumujib.artic.cache.models.mappers;

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
public final class ArticleCacheModelMapper_Factory implements Factory<ArticleCacheModelMapper> {
  private final Provider<AuthorModelMapper> authorModelMapperProvider;

  private final Provider<CategoriesModelMapper> categoriesModelMapperProvider;

  public ArticleCacheModelMapper_Factory(Provider<AuthorModelMapper> authorModelMapperProvider,
      Provider<CategoriesModelMapper> categoriesModelMapperProvider) {
    this.authorModelMapperProvider = authorModelMapperProvider;
    this.categoriesModelMapperProvider = categoriesModelMapperProvider;
  }

  @Override
  public ArticleCacheModelMapper get() {
    return newInstance(authorModelMapperProvider.get(), categoriesModelMapperProvider.get());
  }

  public static ArticleCacheModelMapper_Factory create(
      Provider<AuthorModelMapper> authorModelMapperProvider,
      Provider<CategoriesModelMapper> categoriesModelMapperProvider) {
    return new ArticleCacheModelMapper_Factory(authorModelMapperProvider, categoriesModelMapperProvider);
  }

  public static ArticleCacheModelMapper newInstance(AuthorModelMapper authorModelMapper,
      CategoriesModelMapper categoriesModelMapper) {
    return new ArticleCacheModelMapper(authorModelMapper, categoriesModelMapper);
  }
}
