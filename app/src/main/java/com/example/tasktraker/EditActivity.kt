package com.example.tasktraker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasktraker.databinding.ActivityEditBinding



class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve a binding object that allows you to refer to views by id name
        // Names are converted from snake case to camel case.
        // For example, a View with the id word_one is referenced as binding.wordOne
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editText2.setText(intent?.extras?.getString("Task").toString())
        binding.saveButton.setOnClickListener{
            val modifiedText = binding.editText2.text.toString()
            // Prepare data intent
            val data = Intent()
            // Pass relevant data back as a result
            data.putExtra("modifiedText", modifiedText)
            data.putExtra("position", intent?.extras?.getInt("TaskId")) // ints work too
            // Activity finished ok, return the data
            setResult(200, data) // set result code and bundle data for response
            finish() // closes the activity, pass data to parent
        }
    }
}