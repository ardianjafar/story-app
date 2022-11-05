package com.manyan.mystoryapp.dataApp.mapper

import com.manyan.mystoryapp.dataApp.local.entity.StoryEntity
import com.manyan.mystoryapp.dataApp.model.Story

fun storyToStoryEntity(story: Story): StoryEntity {
    return StoryEntity(
        id = story.id,
        photoUrl = story.photoUrl
    )
}