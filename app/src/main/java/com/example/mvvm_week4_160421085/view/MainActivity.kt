package com.example.mvvm_week4_160421085.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mvvm_week4_160421085.R
import com.example.mvvm_week4_160421085.databinding.ActivityMainBinding
import com.example.mvvm_week4_160421085.util.createNotificationChannel
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }
    companion object {
        private var instance: MainActivity? = null
        fun showNotification(title: String, content: String, icon: Int) {
            val channelId =
                "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val builder =
                NotificationCompat.Builder(
                    instance!!.applicationContext,
                    channelId
                ).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }
            val manager =
                NotificationManagerCompat.from(instance!!.applicationContext.applicationContext!!)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
                return
            }
            manager.notify(1001, builder.build())
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("permission", "granted")
                //create notif chanel util
                createNotificationChannel(this,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                    getString(R.string.app_name), "App notification channel.")
            } else {
                Log.d("permission", "deny")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")
    }

}