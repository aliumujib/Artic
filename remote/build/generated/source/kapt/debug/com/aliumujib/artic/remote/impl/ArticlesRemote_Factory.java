package com.aliumujib.artic.remote.impl;

import com.aliumujib.artic.remote.api.WordPressAPI;
import com.aliumujib.artic.remote.mapper.PostsMapper;
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
public final class ArticlesRemote_Factory implements Factory<ArticlesRemote> {
  private final Provider<WordPressAPI> wordPressAPIProvider;

  private final Provider<PostsMapper> postsMapperProvider;

  public ArticlesRemote_Factory(Provider<WordPressAPI> wordPressAPIProvider,
      Provider<PostsMapper> postsMapperProvider) {
    this.wordPressAPIProvider = wordPressAPIProvider;
    this.postsMapperProvider = postsMapperProvider;
  }

  @Override
  public ArticlesRemote get() {
    return newInstance(wordPressAPIProvider.get(), postsMapperProvider.get());
  }

  public static ArticlesRemote_Factory create(Provider<WordPressAPI> wordPressAPIProvider,
      Provider<PostsMapper> postsMapperProvider) {
    return new ArticlesRemote_Factory(wordPressAPIProvider, postsMapperProvider);
  }

  public static ArticlesRemote newInstance(WordPressAPI wordPressAPI, PostsMapper postsMapper) {
    return new ArticlesRemote(wordPressAPI, postsMapper);
  }
}
