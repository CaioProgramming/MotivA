package com.ilustris.motiv.tools;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

public class Utils {
    public static final int PICK_IMAGE = 1234;

    public static void pickimage(Activity activity) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        activity.startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE);
    }
}
