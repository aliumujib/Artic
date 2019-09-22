package com.aliumujib.artic.remote.impl

import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.remote.api.WordPressAPI
import com.aliumujib.artic.remote.mapper.PostsMapper
import com.aliumujib.artic.data.repositories.contracts.remote.IArticlesRemote
import io.reactivex.Observable
import javax.inject.Inject

class ArticlesRemote @Inject constructor(var wordPressAPI: WordPressAPI,
                                         var postsMapper: PostsMapper
) : IArticlesRemote {

    override fun getArticles(page: Int): Observable<List<ArticleEntity>> {

        return wordPressAPI.getPostByPage(page,
            PER_PAGE
        ).map {
            postsMapper.mapModelList(it.posts)
        }
    }

    override fun searchArticles(search: String, page: Int): Observable<List<ArticleEntity>> {
        return wordPressAPI.getSearchPosts(search,
            PER_PAGE
        ).map {
            postsMapper.mapModelList(it.posts)
        }
    }

    companion object {
        val PER_PAGE = 15
    }

}