package com.fandango.swanson.ron

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.evernote.android.job.DailyJob
import com.evernote.android.job.JobRequest
import java.util.*
import java.util.concurrent.TimeUnit

class QuoteNotificationJob : DailyJob() {

    private val context = QuoteApp.instance

    override fun onRunDailyJob(params: Params): DailyJobResult {
        SwansonQuoteRepository().getQuote({
            showQuoteNotification(it)
        }, {
            // Do nothing
        })

        return DailyJobResult.SUCCESS
    }

    private fun showQuoteNotification(quote: String?) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java),
            0
        )

        val channelId = "quote"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Ron Swanson Quotes",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Ron Swanson Quotes"
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(context.getString(R.string.swanson_says))
            .setContentText(quote)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(quote))
            .build()

        NotificationManagerCompat.from(context).notify(Random().nextInt(), notification)
    }

    companion object {
        const val TAG = "QuoteNotificationJob"

        fun schedule() {
            val builder = JobRequest.Builder(TAG)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
            DailyJob.schedule(builder, TimeUnit.HOURS.toMillis(8),
                TimeUnit.HOURS.toMillis(10))
        }
    }
}