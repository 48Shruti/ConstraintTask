package com.shruti.constrainttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shruti.constrainttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnui.setOnClickListener {
            val intent = Intent(this,UITaskActivity::class.java)
            startActivity(intent)
        }
        binding.btnList.setOnClickListener {
            val intent = Intent(this,ListViewActivity::class.java)
            startActivity(intent)
        }
        binding.btnspinPos.setOnClickListener {
            val intent = Intent(this,SpinnerPosActivity::class.java)
            startActivity(intent)
        }
    }
}