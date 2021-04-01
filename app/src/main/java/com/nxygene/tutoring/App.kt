package com.nxygene.tutoring

import android.app.Application
import com.backendless.Backendless

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Backendless.initApp(this,"748B552E-F3EB-9C9A-FFC1-4B35437E6C00","A21B1C9F-9F0A-446B-B108-140A90E6D7F0")

    }
}