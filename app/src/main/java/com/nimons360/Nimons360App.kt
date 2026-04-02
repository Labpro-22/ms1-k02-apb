package com.nimons360

import android.app.Application
import com.nimons360.data.local.NimonsDatabase
import com.nimons360.data.remote.api.ApiClient
import com.nimons360.util.SessionManager

class Nimons360App : Application() {

    lateinit var database: NimonsDatabase
        private set

    lateinit var sessionManager: SessionManager
        private set

    lateinit var apiClient: ApiClient
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Initialize database
        database = NimonsDatabase.getInstance(this)

        // Initialize session manager
        sessionManager = SessionManager(this)

        // Initialize API client
        apiClient = ApiClient(sessionManager)
    }

    companion object {
        lateinit var instance: Nimons360App
            private set
    }
}
