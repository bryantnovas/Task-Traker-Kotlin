package com.example.tasktraker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasktraker.adapter.ItemAdapter
import com.example.tasktraker.data.Datasource
import com.example.tasktraker.databinding.ActivityMainBinding
import com.example.tasktraker.model.Task

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val itemAdapter = ItemAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerview = binding.recyclerView
        recyclerview.adapter = itemAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        itemAdapter.submitList(Datasource.getAll())

        binding.addButton.setOnClickListener {
            val text = binding.editText.text.toString()
            if(text == ""){
                Toast.makeText(this, "Task can't be empty!", Toast.LENGTH_SHORT).show()
            } else {
                Datasource.add(Task(text, Datasource.getSize()))
                itemAdapter.submitList(Datasource.getAll())
                binding.editText.setText("")
                hideSoftKeyboard(binding.root)
            }
        }
    }
    fun hideSoftKeyboard(view: View) {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    override fun onActivityResult(requestCode : Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // REQUEST_CODE is defined above
        if (resultCode == 200) {
            // Extract name value from result extras
            val id = data!!.extras!!.getInt("position")
            val string = data.extras?.getString("modifiedText")
            if (string != null) {
                Datasource.set(id, string)
                itemAdapter.notifyItemChanged(id)
            }

        }
    }
}