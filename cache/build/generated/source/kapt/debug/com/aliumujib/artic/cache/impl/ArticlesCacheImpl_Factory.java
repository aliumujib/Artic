package com.aliumujib.artic.cache.impl;

import com.aliumujib.artic.cache.models.mappers.ArticleCacheModelMapper;
import com.aliumujib.artic.cache.room.ArticlesDao;
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
public final class ArticlesCacheImpl_Factory implements Factory<ArticlesCacheImpl> {
  private final Provider<ArticlesDao> articlesDaoProvider;

  private final Provider<ArticleCacheModelMapper> articleCacheModelMapperProvider;

  private final Provider<CacheTimeManager> cacheTimeManagerProvider;

  public ArticlesCacheImpl_Factory(Provider<ArticlesDao> articlesDaoProvider,
      Provider<ArticleCacheModelMapper> articleCacheModelMapperProvider,
      Provider<CacheTimeManager> cacheTimeManagerProvider) {
    this.articlesDaoProvider = articlesDaoProvider;
    this.articleCacheModelMapperProvider = articleCacheModelMapperProvider;
    this.cacheTimeManagerProvider = cacheTimeManagerProvider;
  }

  @Override
  public ArticlesCacheImpl get() {
    return newInstance(articlesDaoProvider.get(), articleCacheModelMapperProvider.get(), cacheTimeManagerProvider.get());
  }

  public static ArticlesCacheImpl_Factory create(Provider<ArticlesDao> articlesDaoProvider,
      Provider<ArticleCacheModelMapper> articleCacheModelMapperProvider,
      Provider<CacheTimeManager> cacheTimeManagerProvider) {
    return new ArticlesCacheImpl_Factory(articlesDaoProvider, articleCacheModelMapperProvider, cacheTimeManagerProvider);
  }

  public static ArticlesCacheImpl newInstance(ArticlesDao articlesDao,
      ArticleCacheModelMapper articleCacheModelMapper, CacheTimeManager cacheTimeManager) {
    return new ArticlesCacheImpl(articlesDao, articleCacheModelMapper, cacheTimeManager);
  }
}
