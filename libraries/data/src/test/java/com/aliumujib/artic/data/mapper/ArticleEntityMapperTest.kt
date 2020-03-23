package com.aliumujib.artic.data.mapper

import com.aliumujib.artic.data.DummyDataFactory
import com.aliumujib.artic.data.model.ArticleEntity
import com.aliumujib.artic.domain.models.Article
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleEntityMapperTest {

    private lateinit var articleEntityMapper: ArticleEntityMapper

    private var authorEntityMapper: AuthorEntityMapper = AuthorEntityMapper()
    private var categoryEntityMapper: CategoryEntityMapper = CategoryEntityMapper()

    @Before
    fun setup() {
        articleEntityMapper = ArticleEntityMapper(authorEntityMapper, categoryEntityMapper)
    }


    @Test
    fun `check that articleEntity mapped to domain Article contains correct info`() {
        var articleEntity = DummyDataFactory.makeRandomArticleEntity()
        var article = articleEntityMapper.mapFromEntity(articleEntity)
        assertEqualData(articleEntity, article)
    }


    @Test
    fun `check that article mapped to data ArticleEntity contains correct info`() {
        var article = DummyDataFactory.makeRandomArticle()
        var articleEntity = articleEntityMapper.mapToEntity(article)
        assertEqualData(articleEntity, article)
    }

    private fun assertEqualData(entity: ArticleEntity, domain: Article) {
        assertEquals(entity.id, domain.id)
        assertEquals(entity.type, domain.type)
        assertEquals(entity.slug, domain.slug)
        assertEquals(entity.url, domain.url)
        assertEquals(entity.status, domain.status)
        assertEquals(entity.title, domain.title)
        assertEquals(entity.title_plain, domain.title_plain)
        assertEquals(entity.content, domain.content)
        assertEquals(entity.excerpt, domain.excerpt)
        assertEquals(entity.modified, domain.modified)
        assertEquals(entity.thumbnail, domain.thumbnail)
        assertEquals(entity.imageURL, domain.fullImageURL)
        assertEquals(entity.comment_count, domain.comment_count)
        assertEquals(entity.isBookmarked, domain.isBookmarked)
    }

}