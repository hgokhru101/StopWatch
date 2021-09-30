package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.seconds().observe(this, Observer {
            textView.text= it.toString()
        })
        viewModel.finished.observe(this, Observer {
            if(it){
                Toast.makeText(this,"finished!",Toast.LENGTH_SHORT).show()
            }
        })
        start.setOnClickListener {
            if(input_num.text.isEmpty() || input_num.text.length < 4) {
             Toast.makeText(this,"Invalid number",Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.timerValue.value = input_num.text.toString().toLong()
                viewModel.startTimer()
            }
        }
        stop.setOnClickListener {
            textView.text = "0"
            viewModel.stopTimer()
        }
    }
}
