package com.ilustris.motiv.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.github.mmin18.widget.RealtimeBlurView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ilustris.motiv.R;
import com.ilustris.motiv.beans.Pics;
import com.ilustris.motiv.beans.Quotes;
import com.ilustris.motiv.db.Picsdb;

import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.ilustris.motiv.tools.Utils.RC_SIGN_IN;

public class Alert implements Dialog.OnShowListener, Dialog.OnDismissListener {
    private Activity activity;
    RealtimeBlurView blur;

    public Alert(Activity activity) {
        this.activity = activity;
        this.blur= activity.findViewById(R.id.rootblur);
        login();

    }

    public void MessageAlert(Drawable icondraw,String message){
        final Dialog dialog = new Dialog(activity,R.style.Dialog_No_Border);
        dialog.setContentView(R.layout.message_dialog);
        dialog.setOnShowListener(this);
        dialog.setOnDismissListener(this);
       ImageView icon = dialog.findViewById(R.id.icon);
       TextView messagetxt = dialog.findViewById(R.id.message);
       Button button = dialog.findViewById(R.id.button);
       icon.setImageDrawable(icondraw);
       messagetxt.setText(message);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.dismiss();
           }
       });
       dialog.show();



    }

    public void AddIcon(final Uri uri){
        Dialog dialog= new Dialog(activity,R.style.Dialog_No_Border);
        dialog.setContentView(R.layout.addicon_layout);
        dialog.setOnShowListener(this);
        dialog.setOnDismissListener(this);
        CircleImageView imageView = dialog.findViewById(R.id.pic);
        final EditText iconanme = dialog.findViewById(R.id.iconame);
        Button save = dialog.findViewById(R.id.save);
        imageView.setImageURI(uri);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picsdb picsdb = new Picsdb(activity);
                picsdb.AddIcon(iconanme.getText().toString(),uri);
            }
        });
        dialog.show();
    }

    public void login(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    //new AuthUI.IdpConfig.FacebookBuilder().build(),
                    //new AuthUI.IdpConfig.TwitterBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.EmailBuilder().build());
            activity.startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setLogo(R.mipmap.ic_launcher)
                    .setAvailableProviders(providers)
                    .setTheme(R.style.AppTheme)
                    .build(),RC_SIGN_IN);
        }
    }


    @Override
    public void onShow(DialogInterface dialogInterface) {
        Animation in = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
        blur.setVisibility(View.VISIBLE);
        blur.startAnimation(in);

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Animation out = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        blur.startAnimation(out);
        blur.setVisibility(View.GONE);
    }
}
