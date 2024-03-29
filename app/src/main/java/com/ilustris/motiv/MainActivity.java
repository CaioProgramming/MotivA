package com.ilustris.motiv;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.ilustris.motiv.adapters.HomePager;
import com.ilustris.motiv.tools.Alert;
import com.ilustris.motiv.tools.Utils;



import static com.ilustris.motiv.tools.Utils.REQUEST_READ_PERMISSION;

public class MainActivity extends AppCompatActivity {

     private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Alert alert = new Alert(this);
        alert.login();
       requestPermission();





    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_PERMISSION);
        }
    }


    private void initView() {

         toolbar = findViewById(R.id.toolbar);
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        setSupportActionBar(toolbar);
        HomePager homepager = new HomePager(getSupportFragmentManager());
        pager.setAdapter(homepager);
        tabs.setupWithViewPager(pager);
        tabs.getTabAt(0).setText("Home");
        tabs.getTabAt(1).setText("Ícones");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Utils.PICK_IMAGE){
            if (resultCode == RESULT_OK){
                Alert alert = new Alert(this);
                alert.AddIcon(data.getData(),true);
            }else{
                Alert alert = new Alert(this);
                alert.MessageAlert(getDrawable(R.drawable.ic_cancel),"Erro ao retornar imagem");
            }
        }else if(requestCode == Utils.RC_SIGN_IN){
            if (resultCode == RESULT_OK) {
                Alert alert = new Alert(this);
                alert.MessageAlert(getDrawable(R.drawable.ic_success),"Login realizado com sucesso");
            }else{
                Alert alert = new Alert(this);
                alert.MessageAlert(getDrawable(R.drawable.ic_cancel),"Erro ao realizar login");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




}
