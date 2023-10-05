package eu.tutorials.a7minuteworkout

import android.app.Application

class WorkoutApp: Application() {
    val db: HistoryDB by lazy {
        HistoryDB.getInstance(this)
    }
}