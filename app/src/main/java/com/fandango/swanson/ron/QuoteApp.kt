package com.fandango.swanson.ron

import android.app.Application
import com.evernote.android.job.JobManager

class QuoteApp : Application() {

    companion object {
        lateinit var instance: QuoteApp
            private set
    }

    override fun onCreate() {
        instance = this

        super.onCreate()

        JobManager.create(this).addJobCreator {
            return@addJobCreator when (it) {
                QuoteNotificationJob.TAG ->
                    QuoteNotificationJob()
                else ->
                    null
            }
        }
        QuoteNotificationJob.schedule()
    }
}