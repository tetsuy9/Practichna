package com.example.practichna

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.imageview.ShapeableImageView
//import kotlinx.android.synthetic.main.activity_menu.*
import java.net.URI

class MenuActivity : AppCompatActivity() {

    private var mName: TextView? = null
    private var mEmail: TextView? = null
    lateinit var ref: SharedPreferences
    private var clickImg: ShapeableImageView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        mName = findViewById(R.id.name)
        mEmail = findViewById(R.id.email)
        ref = getSharedPreferences("Myapp", MODE_PRIVATE)
        mName?.text = ref.getString("name", "")
        mEmail?.text = ref.getString("email", "")

        val btnTeachers = findViewById<Button>(R.id.buttonTeachers)
        val btnGroups = findViewById<Button>(R.id.buttonGroups)

        btnTeachers.setOnClickListener {
            val intent = Intent(this,TeachersActivity::class.java)
            startActivity(intent)
        }
        btnGroups.setOnClickListener {
            val intent = Intent(this,GroupActivity::class.java)
            startActivity(intent)
        }

        clickImg = findViewById<ShapeableImageView>(R.id.imageView)

        clickImg?.setOnClickListener(View.OnClickListener(){
                ImagePicker.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            val uri: Uri = data?.data!!
        clickImg?.setImageURI(uri)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            logout()
        }

        return true
    }

    private fun logout() {
        ref.edit().putBoolean("login", false).apply()
        startLogin()

    }

    private fun startLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}