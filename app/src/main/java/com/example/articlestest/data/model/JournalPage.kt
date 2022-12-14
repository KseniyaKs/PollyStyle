package com.example.articlestest.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JournalPage(
    val id: String,
    val comments: List<Comment>,
    val likeCount: Number,
    val isLike: Boolean,
    val isCommented: Boolean,
    val journal: Journal,
    val pageNumber: Number,
    val pageFile: String
) : Parcelable

@Parcelize
data class Comment(
    val id: String,
    val user: User,
    val commentText: String,
) : Parcelable


@Parcelize
data class User(
    val username: String,
    val firstName: String,
    val surname: String
) : Parcelable


