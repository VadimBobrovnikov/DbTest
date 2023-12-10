package com.example.myapplication



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData

import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDB.getDb(this)
        db.getDao().getAllItems().asLiveData().observe(this){list ->
            binding.textView.text = ""
            list.forEach{

                val text = "Id: ${it.id} Name: ${it.name} Tel: ${it.tel_num} \n"
                binding.textView.append(text)
            }
        }
        binding.button.setOnClickListener {
            val item = Item(null, binding.edName.text
                .toString(),binding.edTele.text.toString())
            Thread{
                db.getDao().insertItem(item)
            }.start()

        }



    }
}