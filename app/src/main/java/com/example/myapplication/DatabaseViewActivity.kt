package com.example.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.Surface
import com.example.numScanner.numScanner.Theme.JetpackComposeMLKitTutorialTheme
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme

class DatabaseViewActivity : AppCompatActivity() {
    private val db: MainDB by lazy { MainDB.getDb(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeMLKitTutorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DatabaseViewScreen(db)
                }
            }
        }
    }
}