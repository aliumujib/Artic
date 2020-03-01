package com.aliumujib.artic.remote.mapper

import com.aliumujib.artic.data.model.CommentEntity
import com.aliumujib.artic.remote.models.Comment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CommentMapper @Inject constructor(): RemoteModelMapper<Comment, CommentEntity> {

    override fun mapFromModel(model: Comment): CommentEntity {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: Date = safeParse(format, model.date)
        return CommentEntity(model.id, model.name, model.url, date, model.content, model.parent)
    }

}