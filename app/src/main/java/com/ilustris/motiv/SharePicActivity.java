package com.ilustris.motiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.ilustris.motiv.tools.Alert;

public class SharePicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.println(Log.INFO, "Icon", "Shared image to app");
        Intent receivedIntent = getIntent();
        String receivedAction = receivedIntent.getAction();
        String receivedType = receivedIntent.getType();
        if (receivedAction.equals(Intent.ACTION_SEND)) {
            //content is being shared }else if(receivedAction.equals(Intent.ACTION_MAIN)){ //app has been launched directly, not from share list }

            if(receivedType.startsWith("image/")) {
                Uri receivedUri = (Uri)receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                 Alert alert = new Alert(this);
                alert.AddIcon(receivedUri,false);
            }


        }
    }
}
