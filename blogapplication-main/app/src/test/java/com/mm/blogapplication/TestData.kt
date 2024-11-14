package com.mm.blogapplication

import com.mm.domain.model.Blog
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

fun getDummyOwner() =
    Owner(
        firstName = "1",
        id = "1",
        lastName = "1",
        picture = "1",
        title = "1"
    )

fun getDummyBlogList() = listOf(
    Blog(
        id = "1",
        image = "1",
        likes = 1,
        owner = getDummyOwner(),
        publishDate = "1",
        tags = listOf("x", "y", "z"),
        text = "String"
    )
)