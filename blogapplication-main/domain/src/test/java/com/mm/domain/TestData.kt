package com.mm.domain

import com.mm.domain.model.Blog
import com.mm.domain.model.Blogs
import com.mm.domain.model.Owner

fun getDummyBlog() =
    Blog(
        id = "1",
        image = "1",
        likes = 1,
        owner = getDummyOwner(),
        publishDate = "1",
        tags = listOf("x", "y", "z"),
        text = "String"
    )

fun getDummyBlogsData() =
    Blogs(
        limit = 1,
        page = 1,
        total = 1,
        data = listOf(getDummyBlog()),
    )

fun getDummyOwner() =
    Owner(
        firstName = "1",
        id = "1",
        lastName = "1",
        picture = "1",
        title = "1"
    )