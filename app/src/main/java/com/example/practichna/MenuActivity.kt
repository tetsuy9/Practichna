package com.example.practichna

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView

class MenuActivity : AppCompatActivity() {

    private var mName: TextView? = null
    private var mEmail: TextView? = null
    lateinit var ref: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

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
            val intent = Intent(this,GroupsActivity::class.java)
            startActivity(intent)
        }
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