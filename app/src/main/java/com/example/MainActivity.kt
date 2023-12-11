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




        private lateinit var db: MainDB

        lateinit var binding: ActivityMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            db = MainDB.getDb(applicationContext)
            val db = MainDB.getDb(this)
            db.getDao().getAllItems().asLiveData().observe(this) { list ->
                binding.textView.text = ""
                list.forEach {

                    val text = "Id: ${it.id} Name: ${it.name}  \n"
                    binding.textView.append(text)
                }
            }
            binding.button.setOnClickListener {
                val item = Item(
                    null, binding.edName.text
                        .toString(), binding.edTele.text.toString()
                )
                Thread {
                    db.getDao().insertItem(item)
                }.start()

            }




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