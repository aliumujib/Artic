package com.aliumujib.artic.remote.impl

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.mapper.PostsMapper
import com.aliumujib.artic.data.repositories.articles.remote.IArticlesRemote
import javax.inject.Inject

class ArticlesRemote @Inject constructor(
    var wordPressAPI: WordPressAPI,
    var postsMapper: PostsMapper
) : IArticlesRemote {

    override suspend fun getArticles(page: Int): List<ArticleEntity> {
        return postsMapper.mapModelList(wordPressAPI.getPostByPage(page, PER_PAGE).posts)
    }

    override suspend fun getArticle(id: Int): ArticleEntity {
        return postsMapper.mapFromModel(wordPressAPI.getPostDetialsById(id).post)
    }

    override suspend fun searchArticles(search: String, page: Int): List<ArticleEntity> {
        return postsMapper.mapModelList(wordPressAPI.getSearchPosts(search, PER_PAGE).posts)
    }

    override suspend fun getArticlesByCategoryId(categoryId: Int, page: Int): List<ArticleEntity> {
        return postsMapper.mapModelList(wordPressAPI.getCategoryDetailsByPage(categoryId, page, PER_PAGE).posts)
    }

    companion object {
        private const val PER_PAGE = 5
    }

}