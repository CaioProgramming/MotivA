package com.ilustris.motiv.tools

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.github.mmin18.widget.RealtimeBlurView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ilustris.motiv.R
import com.ilustris.motiv.model.Picsdb

import java.util.Arrays

import de.hdodenhof.circleimageview.CircleImageView

import com.ilustris.motiv.tools.Utils.RC_SIGN_IN

class Alert(private val activity: Activity) : DialogInterface.OnShowListener, DialogInterface.OnDismissListener {
    internal var blur: RealtimeBlurView? = null


    private val onDismissListener: DialogInterface.OnDismissListener? = null


    init {
        this.blur = activity.findViewById(R.id.rootblur)
        login()

    }


    fun MessageAlert(icondraw: Drawable, message: String) {
        val dialog = Dialog(activity, R.style.Dialog_No_Border)
        dialog.setContentView(R.layout.message_dialog)
        dialog.setOnShowListener(this)
        dialog.setOnDismissListener(this)
        val icon = dialog.findViewById<ImageView>(R.id.icon)
        val messagetxt = dialog.findViewById<TextView>(R.id.message)
        val button = dialog.findViewById<Button>(R.id.button)
        icon.setImageDrawable(icondraw)
        messagetxt.text = message
        button.setOnClickListener { dialog.dismiss() }
        dialog.show()


    }

    fun AddIcon(uri: Uri, ismainact: Boolean) {
        val dialog = Dialog(activity, R.style.Dialog_No_Border)
        dialog.setContentView(R.layout.addicon_layout)
        dialog.setOnShowListener(this)
        dialog.setOnDismissListener(this)
        val imageView = dialog.findViewById<CircleImageView>(R.id.pic)
        val iconanme = dialog.findViewById<EditText>(R.id.iconame)
        val save = dialog.findViewById<Button>(R.id.save)
        Glide.with(activity).load(uri).into(imageView)
        save.setOnClickListener {
            val picsdb = Picsdb(activity)
            picsdb.AddIcon(iconanme.text.toString(), imageView, dialog)
            save.isEnabled = false
        }
        dialog.show()
        if (!ismainact) {
            dialog.setOnDismissListener { activity.finish() }
        }
    }

    fun login() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            val providers = Arrays.asList<AuthUI.IdpConfig>(
                    //new AuthUI.IdpConfig.FacebookBuilder().build(),
                    //new AuthUI.IdpConfig.TwitterBuilder().build(),
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.EmailBuilder().build())
            activity.startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .setLogo(R.mipmap.ic_launcher)
                    .setAvailableProviders(providers)
                    .setTheme(R.style.AppTheme)
                    .build(), RC_SIGN_IN)
        }
    }


    override fun onShow(dialogInterface: DialogInterface) {
        if (blur != null) {
            val `in` = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
            blur!!.visibility = View.VISIBLE
            blur!!.startAnimation(`in`)
        }

    }

    override fun onDismiss(dialogInterface: DialogInterface) {
        if (blur != null) {
            val out = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
            blur!!.startAnimation(out)
            blur!!.visibility = View.GONE
        }
    }
}
