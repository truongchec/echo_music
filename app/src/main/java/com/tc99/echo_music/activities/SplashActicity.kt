package com.tc99.echo_music.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class SplashActicity : AppCompatActivity() {


    var permissionsStrimg = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.MODIFY_AUDIO_SETTINGS,
        android.Manifest.permission.READ_PHONE_STATE,
        android.Manifest.permission.PROCESS_OUTGOING_CALLS,
        android.Manifest.permission.RECORD_AUDIO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.tc99.echo_music.R.layout.activity_plash)
        if (!hasPermissions(this, *permissionsStrimg)) {

            ActivityCompat.requestPermissions(this, permissionsStrimg, 131)
        } else {
            Handler().postDelayed({
                val startAct = Intent(this, MainActivity::class.java)
                startActivity(startAct)
                this.finish()
            }, 1000)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {

            131 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[3] == PackageManager.PERMISSION_GRANTED
                    && grantResults[4] == PackageManager.PERMISSION_GRANTED
                ) {

                    Handler().postDelayed({
                        val startAct = Intent(this, MainActivity::class.java)
                        startActivity(startAct)
                        this.finish()
                    }, 1000)

                } else {
                    Toast.makeText(
                        this,
                        "Plaese Grant all permissions to continue",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                return
            }

            else -> {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                this.finish()
                return
            }
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        var hasAllPermissions = true
        for (permission in permissions) {
            val res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermissions = false
            }
        }
        return hasAllPermissions
    }

}
