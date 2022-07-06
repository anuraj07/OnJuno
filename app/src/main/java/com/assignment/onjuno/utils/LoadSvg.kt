package com.assignment.onjuno.utils

import android.content.Context
import android.widget.ImageView
import com.assignment.onjuno.R
import com.pixplicity.sharp.Sharp
import java.io.IOException
import java.io.InputStream
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class LoadSvg {
    private var httpClient: OkHttpClient? = null

    fun fetchSvg(
        context: Context, url: String?,
        target: ImageView
    ) {
        if (httpClient == null) {
            httpClient = OkHttpClient.Builder()
                .cache(
                    Cache(
                        context.cacheDir,
                        5 * 1024 * 1014
                    )
                )
                .build()
        }

        val request: Request? = url?.let { Request.Builder().url(it).build() }
        if (request != null) {
            httpClient!!.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    target.setImageResource(
                        R.drawable.ic_baseline_enhanced_encryption_24
                    )
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    val stream: InputStream = response.body()?.byteStream() ?: return
                    Sharp.loadInputStream(stream).into(target)
                    stream.close()
                }
            })
        }
    }
}