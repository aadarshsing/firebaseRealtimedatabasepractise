package com.example.firebaserealtimedatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabase.databinding.ListviewBinding
import com.example.firebaserealtimedatabase.studentModel

class databaseAdapter(private val data:List <studentModel>, private val onClickEvent: onItemclicklistener):RecyclerView.Adapter<databaseAdapter.viewholder> (){
    class viewholder(private val binding: ListviewBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(data:studentModel){
            binding.name.text = data.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(ListviewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val current = data[position]
        holder.itemView.setOnClickListener{
            onClickEvent.onItemclick(position)
        }
        holder.bind(current)
        
    }

    override fun getItemCount(): Int {
        return data.size
    }
    interface onItemclicklistener{
        fun onItemclick(position: Int)
    }
}