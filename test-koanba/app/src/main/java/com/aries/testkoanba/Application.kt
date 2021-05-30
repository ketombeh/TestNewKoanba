package com.aries.testkoanba

import android.app.Application
import android.content.Context
import com.aries.testkoanba.network.ApiClient
import com.aries.testkoanba.network.ApiInterface

class Application : Application() {
    private lateinit var context: Context
    var apiService: ApiInterface = ApiClient.getClientService().create(ApiInterface::class.java)

    companion object {
        @JvmStatic
        var application: com.aries.testkoanba.Application? = null
        val instance: com.aries.testkoanba.Application
            get() {
                if (application == null) {
                    application = com.aries.testkoanba.Application()
                }
                return application as com.aries.testkoanba.Application
            }
    }

    override fun onCreate() {
        super.onCreate()
        context = baseContext
    }
}