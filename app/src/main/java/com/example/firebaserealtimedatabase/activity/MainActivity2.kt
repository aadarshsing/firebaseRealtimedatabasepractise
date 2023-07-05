package com.example.firebaserealtimedatabase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaserealtimedatabase.databinding.ActivityMain2Binding
import com.example.firebaserealtimedatabase.studentModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private val TAG = "MainActivity2"
    private lateinit var binding: ActivityMain2Binding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().getReference("Students")

        binding.save.setOnClickListener {
            GlobalScope.launch {
                saveInfo()
            }
        }
    }

    suspend fun  saveInfo (){
        val name = binding.name.text.toString()
        val phone = binding.phone.text.toString()
        val address =binding.address.text.toString()

        if(name.isEmpty()){
            binding.name.error = "please enter name"
        }
        if(phone.isEmpty()){
            binding.name.error = "please enter phonenumber"
        }
        if(address.isEmpty()){
            binding.name.error = "please enter address"
        }
        val stdId = databaseReference.push().key!!

        val student = studentModel(stdId,name,phone,address)
        databaseReference.child(stdId).setValue(student).addOnCompleteListener {
            Log.d(TAG,"data inserted succussfuly")

            binding.name.text.clear()
            binding.phone.text.clear()
            binding.address.text.clear()
        }.addOnCanceledListener {
            Log.e(TAG,"data doesnot inserted")
        }

    }
}