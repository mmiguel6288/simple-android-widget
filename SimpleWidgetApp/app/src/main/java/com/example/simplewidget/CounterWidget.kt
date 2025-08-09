package com.example.simplewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.RemoteViews

class CounterWidget : AppWidgetProvider() {
    
    companion object {
        private const val ACTION_INCREMENT = "com.example.simplewidget.INCREMENT"
        private const val ACTION_DECREMENT = "com.example.simplewidget.DECREMENT"
        private const val ACTION_RESET = "com.example.simplewidget.RESET"
        private const val PREFS_NAME = "CounterWidgetPrefs"
        private const val PREF_COUNTER_KEY = "counter_"
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        
        val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val currentCounter = prefs.getInt(PREF_COUNTER_KEY + appWidgetId, 0)
            
            val newCounter = when (intent.action) {
                ACTION_INCREMENT -> currentCounter + 1
                ACTION_DECREMENT -> currentCounter - 1
                ACTION_RESET -> 0
                else -> currentCounter
            }
            
            prefs.edit().putInt(PREF_COUNTER_KEY + appWidgetId, newCounter).apply()
            
            val appWidgetManager = AppWidgetManager.getInstance(context)
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val counter = prefs.getInt(PREF_COUNTER_KEY + appWidgetId, 0)
        
        val views = RemoteViews(context.packageName, R.layout.counter_widget)
        views.setTextViewText(R.id.counter_text, counter.toString())
        
        // Set up button click intents
        views.setOnClickPendingIntent(R.id.btn_increment, getPendingSelfIntent(context, ACTION_INCREMENT, appWidgetId))
        views.setOnClickPendingIntent(R.id.btn_decrement, getPendingSelfIntent(context, ACTION_DECREMENT, appWidgetId))
        views.setOnClickPendingIntent(R.id.btn_reset, getPendingSelfIntent(context, ACTION_RESET, appWidgetId))
        
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
    
    private fun getPendingSelfIntent(context: Context, action: String, appWidgetId: Int): PendingIntent {
        val intent = Intent(context, CounterWidget::class.java)
        intent.action = action
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        return PendingIntent.getBroadcast(context, appWidgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }
}