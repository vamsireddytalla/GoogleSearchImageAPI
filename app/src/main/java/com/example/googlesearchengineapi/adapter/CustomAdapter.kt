package com.example.googlesearchengineapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.googlesearchengineapi.databinding.CustomItemBinding
import com.example.googlesearchengineapi.models.Item

class CustomAdapter(var itemsList:List<Item>,var mContext:Context) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>()
{
    class MyViewHolder(var binding:CustomItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
      var customItemBinding:CustomItemBinding
        init {
            this.customItemBinding=binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val customItemBinding = CustomItemBinding.inflate(layoutInflater)
        return MyViewHolder(customItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=itemsList.get(position)
        Glide.with(mContext)
            .load(item.link)
            .into(holder.customItemBinding.imgView)
        holder.customItemBinding.desc.text=item.snippet.toString()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}