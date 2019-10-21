package com.ilustris.motiv.view

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log

import com.ilustris.motiv.tools.Alert

class SharePicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.println(Log.INFO, "Icon", "Shared image to app")
        val receivedIntent = intent
        val receivedAction = receivedIntent.action
        val receivedType = receivedIntent.type
        if (receivedAction == Intent.ACTION_SEND) {
            //content is being shared }else if(receivedAction.equals(Intent.ACTION_MAIN)){ //app has been launched directly, not from share list }

            if (receivedType!!.startsWith("image/")) {
                val receivedUri = receivedIntent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri
                val alert = Alert(this)
                alert.AddIcon(receivedUri, false)
            }


        }
    }
}
