package com.ilustris.motiv.tools

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object Utils {
    val PICK_IMAGE = 1234
    val RC_SIGN_IN = 123
    val REQUEST_READ_PERMISSION = 786

    fun pickimage(activity: Activity) {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

        activity.startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE)
    }
}
