package com.example.firebaserealtimedatabase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaserealtimedatabase.adapter.databaseAdapter
import com.example.firebaserealtimedatabase.databinding.ActivityMain3Binding
import com.example.firebaserealtimedatabase.studentModel
import com.google.firebase.database.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var studentlist:MutableList<studentModel>
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        studentlist = mutableListOf<studentModel>()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.setHasFixedSize(true)
        GlobalScope.launch {
            getstudentData()}
    }

    private suspend fun getstudentData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Students")
        databaseReference.addValueEventListener(object :ValueEventListener,
            databaseAdapter.onItemclicklistener {

            override fun onDataChange(snapshot: DataSnapshot) {
                studentlist.clear()
                if (snapshot.exists()){
                    for (empsnap in snapshot.children){
                        val stdData = empsnap.getValue(studentModel::class.java)
                        studentlist.add(stdData!!)
                    }
                    val stdadapter = databaseAdapter(studentlist,this)
                    binding.recycler.adapter = stdadapter
                    binding.recycler.setOnClickListener{

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onItemclick(position: Int) {
                val intent = Intent(this@MainActivity3,MainActivity4::class.java)
                intent.putExtra("stdID",studentlist[position].stdId)
                intent.putExtra("stdname",studentlist[position].name)
                intent.putExtra("stdphone",studentlist[position].phone)
                intent.putExtra("stdaddress",studentlist[position].address)
                startActivity(intent)
            }

        })
    }
}