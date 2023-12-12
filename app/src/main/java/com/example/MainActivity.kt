package com.example


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.asLiveData
import com.example.myapplication.Item
import com.example.myapplication.MainDB
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.numScanner.numScanner.MainScreen
import com.example.numScanner.numScanner.Theme.JetpackComposeMLKitTutorialTheme


class MainActivity : AppCompatActivity(){

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val db = MainDB.getDb(this)

            setContent {
                JetpackComposeMLKitTutorialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        MainScreen(db, this)

                    }
                }
            }

        }
    }