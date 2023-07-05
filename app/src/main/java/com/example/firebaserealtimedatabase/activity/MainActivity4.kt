package com.example.firebaserealtimedatabase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.firebaserealtimedatabase.databinding.ActivityMain4Binding
import com.example.firebaserealtimedatabase.studentModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setvalue()
        binding.update.setOnClickListener {
            GlobalScope.launch {
                updatedata(
                    intent.getStringExtra("stdID").toString(),
                    binding.name.text.toString(),
                    binding.phone.text.toString(),
                    binding.address.text.toString()
                )
            }
        }
        binding.delete.setOnClickListener {
            GlobalScope.launch {
                delete(
                    intent.getStringExtra("stdID").toString()
                )
            }

        }
    }
    private fun setvalue(){
        binding.name.setText(intent.getStringExtra("stdname"))
        binding.phone.setText(intent.getStringExtra("stdphone"))
        binding.address.setText(intent.getStringExtra("stdaddress"))
    }
    private fun updatedata(id:String,name:String,phone:String,address:String){
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("Students").child(id)
        val student = studentModel(id,name,phone,address)
        databaseReference.setValue(student)
    }
    private  fun delete(id: String){
        val databaseReference = FirebaseDatabase.getInstance().getReference("Students").child(id)
        databaseReference.removeValue().addOnSuccessListener {
            val intent = Intent(this,MainActivity3::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{

        }
    }
}