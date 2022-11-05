package com.manyan.mystoryapp.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.room.Room
import com.manyan.mystoryapp.R
import com.manyan.mystoryapp.dataApp.local.StoryAppDatabase
import com.manyan.mystoryapp.dataApp.local.entity.StoryEntity
import com.manyan.mystoryapp.utils.ConstVal.DB_NAME
import com.manyan.mystoryapp.utils.urlToBitmap

internal class StackRemoteViewsFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory {

    private var stories: MutableList<StoryEntity> = mutableListOf()

    override fun onCreate() {
    }

    override fun onDataSetChanged() {
        val database = Room.databaseBuilder(
            context.applicationContext, StoryAppDatabase::class.java,
            DB_NAME
        ).build()
        database.getStoryDao().getAllStories().forEach {
            stories.add(
                StoryEntity(
                    it.id,
                    it.photoUrl
                )
            )
        }
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int = stories.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.story_widget_item)
        rv.setImageViewBitmap(R.id.imgStory, urlToBitmap(stories[position].photoUrl))

        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(p0: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}