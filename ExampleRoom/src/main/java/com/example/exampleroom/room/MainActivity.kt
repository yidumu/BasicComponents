package com.example.exampleroom.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exampleroom.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        AppDatabase.getInstance(applicationContext).userDao().getAll()
    }

}