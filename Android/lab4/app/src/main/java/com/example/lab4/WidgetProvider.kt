package com.example.lab4

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.app.AlarmManager
import android.widget.Toast
import android.app.PendingIntent
import android.app.NotificationManager
import android.support.v4.app.NotificationCompat
import android.app.NotificationChannel
import android.os.Build
import android.content.ComponentName
import android.widget.RemoteViews
import java.util.*


class WidgetProvider : AppWidgetProvider()
{
    companion object {
        var manager: NotificationManager? = null
        fun getManager(context: Context): NotificationManager
        {
            if (manager == null)
            {
                manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            return manager as NotificationManager
        }
    }

    private fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int)
    {
        val sharedPreferences = context.getSharedPreferences(MainActivity.PREFERENCES, Context.MODE_PRIVATE)
        val views = RemoteViews(context.packageName, R.layout.widgetmanager)
        val date = sharedPreferences.getLong(MainActivity.WIDGET_DATE, -1)

        if (date == -1L)
        {
            views.setTextViewText(R.id.appwidget_text, "-")
        }
        else
        {
            val calendar1 = GregorianCalendar.getInstance()
            val calendar2 = GregorianCalendar.getInstance()
            calendar2.timeInMillis = date
            val year = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR)
            var days = calendar2.get(Calendar.DAY_OF_YEAR) - calendar1.get(Calendar.DAY_OF_YEAR)
            days += year * 365
            if(days < 0)
            {
                views.setTextViewText(R.id.appwidget_text, "-")
            }
            else
            {
                views.setTextViewText(R.id.appwidget_text, Integer.toString(days))
                if(days == 0)
                {
                    showNotification(context)
                }
                else
                {
                    scheduleWakeUp(context)
                }
            }
        }

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray)
    {
        for (appWidgetId in appWidgetIds)
        {
            updateWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent)
    {
        super.onReceive(context, intent)

        onUpdate(
            context,
            AppWidgetManager.getInstance(context),
            AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, WidgetProvider::class.java))
        )

    }

    private fun showNotification(context: Context)
    {
        val CHANNEL_ID = "com.example.lab4.CHANNEL_FOR_NOTIFICATION"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "NOTIFICATIONS CHANNEL"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.widget_icon)
            .setContentTitle("Lab")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Привет"))
            .setAutoCancel(true)
        getManager(context).notify(12, builder.build())
    }

    private fun scheduleWakeUp(context: Context)
    {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, WidgetProvider::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val calendar = MainActivity.calendar
        var notification = "Notification date: " + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "."
        notification += Integer.toString(calendar.get(Calendar.MONTH)) + "."
        notification += Integer.toString(calendar.get(Calendar.YEAR)) + " "
        notification += Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + convertMinute(calendar.get(Calendar.MINUTE))

        Toast.makeText(context, notification, Toast.LENGTH_SHORT).show()
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
    private fun convertMinute(minute: Int): String
    {
        return when (minute) {
            0 -> "0" + Integer.toString(minute)
            1 -> "0" + Integer.toString(minute)
            2 -> "0" + Integer.toString(minute)
            3 -> "0" + Integer.toString(minute)
            4 -> "0" + Integer.toString(minute)
            5 -> "0" + Integer.toString(minute)
            6 -> "0" + Integer.toString(minute)
            7 -> "0" + Integer.toString(minute)
            8 -> "0" + Integer.toString(minute)
            9 -> "0" + Integer.toString(minute)
            else -> Integer.toString(minute)
        }
    }
}