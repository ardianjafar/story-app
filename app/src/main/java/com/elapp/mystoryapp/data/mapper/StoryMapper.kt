package com.elapp.mystoryapp.data.mapper

import com.elapp.mystoryapp.data.local.entity.StoryEntity
import com.elapp.mystoryapp.data.model.Story

fun storyToStoryEntity(story: Story): StoryEntity {
    return StoryEntity(
        id = story.id,
        photoUrl = story.photoUrl
    )
}