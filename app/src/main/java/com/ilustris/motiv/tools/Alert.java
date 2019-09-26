package com.ilustris.motiv.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mmin18.widget.RealtimeBlurView;
import com.ilustris.motiv.R;
import com.ilustris.motiv.beans.Pics;
import com.ilustris.motiv.beans.Quotes;

public class Alert implements Dialog.OnShowListener, Dialog.OnDismissListener {
    private Activity activity;
    RealtimeBlurView blurView;

    public Alert(Activity activity) {
        this.activity = activity;
        this.blurView = activity.findViewById(R.id.rootblur);

    }

    public void MessageAlert(Drawable icondraw,String message,Button.OnClickListener onClickListener){
        Dialog dialog = new Dialog(activity,R.style.Dialog_No_Border);
        dialog.setContentView(R.layout.message_dialog);

       ImageView icon = dialog.findViewById(R.id.icon);
       TextView messagetxt = dialog.findViewById(R.id.message);
       Button button = dialog.findViewById(R.id.button);

       icon.setImageDrawable(icondraw);
       messagetxt.setText(message);
       button.setOnClickListener(onClickListener);



    }
    public void MessageAlertImage(Drawable icondraw, String message, Button.OnClickListener onClickListener){
        Dialog dialog = new Dialog(activity,R.style.Dialog_No_Border);
        dialog.setContentView(R.layout.message_dialog);

       ImageView icon = dialog.findViewById(R.id.icon);
       TextView messagetxt = dialog.findViewById(R.id.message);
       Button button = dialog.findViewById(R.id.button);

       icon.setImageDrawable(icondraw);
       messagetxt.setText(message);
       button.setOnClickListener(onClickListener);



    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    @Override
    public void onShow(DialogInterface dialog) {

    }
}
