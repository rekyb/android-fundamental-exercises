package com.dicoding.codelab.mywidgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews

class RandomNumberWidget : AppWidgetProvider() {

    companion object {
        private const val WIDGET_CLICK = "android.appwidget.action.APPWIDGET_UPDATE"
        private const val WIDGET_ID_EXTRA = "widget_id_extra"
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val lastUpdate = "Random: " + NumberGenerator.generate(100)
        val remoteViews = RemoteViews(context.packageName, R.layout.random_number_widget)

        remoteViews.setTextViewText(R.id.appwidget_text, lastUpdate)
        remoteViews.setOnClickPendingIntent(
            R.id.btn_click,
            getPendingSelfIntent(context, appWidgetId)
        )
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (WIDGET_CLICK == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val remoteViews = RemoteViews(context.packageName, R.layout.random_number_widget)
            val lastUpdate = "Random: " + NumberGenerator.generate(100)
            val appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0)

            remoteViews.setTextViewText(R.id.appwidget_text, lastUpdate)
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }

    private fun getPendingSelfIntent(
        context: Context,
        appWidgetId: Int,
    ): PendingIntent {

        val intent = Intent(context, RandomNumberWidget::class.java)

        intent.action = "android.appwidget.action.APPWIDGET_UPDATE"
        intent.putExtra(WIDGET_ID_EXTRA, appWidgetId)

        return PendingIntent.getActivity(
            context,
            appWidgetId,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            } else {
                0
            }
        )
    }
}


