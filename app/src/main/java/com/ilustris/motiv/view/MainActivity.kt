package com.ilustris.motiv.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.ilustris.motiv.R
import com.ilustris.motiv.adapters.HomePager
import com.ilustris.motiv.tools.Alert
import com.ilustris.motiv.tools.Utils


import com.ilustris.motiv.tools.Utils.REQUEST_READ_PERMISSION

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabs: TabLayout? = null
    private var pager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        val alert = Alert(this)
        alert.login()
        requestPermission()


    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_PERMISSION)
        }
    }


    private fun initView() {

        toolbar = findViewById(R.id.toolbar)
        tabs = findViewById(R.id.tabs)
        pager = findViewById(R.id.pager)
        setSupportActionBar(toolbar)
        val homepager = HomePager(supportFragmentManager)
        pager!!.adapter = homepager
        tabs!!.setupWithViewPager(pager)
        tabs!!.getTabAt(0)!!.text = "Home"
        tabs!!.getTabAt(1)!!.text = "Ícones"
        tabs!!.getTabAt(2)!!.text = "Sugestões"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Utils.PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                val alert = Alert(this)
                alert.AddIcon(data!!.data, true)
            } else {
                val alert = Alert(this)
                alert.MessageAlert(getDrawable(R.drawable.ic_cancel), "Erro ao retornar imagem")
            }
        } else if (requestCode == Utils.RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                val alert = Alert(this)
                alert.MessageAlert(getDrawable(R.drawable.ic_success), "Login realizado com sucesso")
            } else {
                val alert = Alert(this)
                alert.MessageAlert(getDrawable(R.drawable.ic_cancel), "Erro ao realizar login")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
