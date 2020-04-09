package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import com.example.background.R
import timber.log.Timber

/**
 * BlurWorker.kt
 * android-workmanager
 * Created by 田村 柾優紀 on 2020/04/09.
 * Copyright © 2020 田村 柾優紀. All rights reserved.
 */
class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val appContext = applicationContext

        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        makeStatusNotification("Blurring image", appContext)
        return try {
            val picture = BitmapFactory.decodeResource(
                    appContext.resources,
                    R.drawable.test)

            val output = blurBitmap(picture, appContext)

            // Write bitmap to a temp file
            val outputUri = writeBitmapToFile(appContext, output)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
            Result.success(outputData)
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Result.failure()
        }
    }
}