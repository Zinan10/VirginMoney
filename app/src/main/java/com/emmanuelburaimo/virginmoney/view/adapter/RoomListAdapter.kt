package com.emmanuelburaimo.virginmoney.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emmanuelburaimo.virginmoney.R
import com.emmanuelburaimo.virginmoney.repository.model.PersonaModel
import com.emmanuelburaimo.virginmoney.repository.model.RoomModel
import com.emmanuelburaimo.virginmoney.util.downloadFromUrl
import com.emmanuelburaimo.virginmoney.util.placeholderProgressBar

class RoomListAdapter (private var roomList: List<RoomModel>) :
    RecyclerView.Adapter<RoomListAdapter.MyViewHolder>() {

    fun updateRoomList(roomList: List<RoomModel>) {

        this.roomList = roomList.filter { it.isOccupied==true }
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_room_model, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        roomList.let {

            if (it.isNotEmpty()) {
                if (it[position].isOccupied==true){
                    holder.textViewRoomId.text = it[position].id
                    holder.textViewIsOccupiedValue.text = it[position].isOccupied.toString()
                    holder.textViewMaxOccupancyValue.text=it[position].maxOccupancy
                    holder.textViewCreatedAtValue.text=it[position].createdAt
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (roomList.isEmpty()) {
            1
        } else {
            roomList.size
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewRoomId: TextView = itemView.findViewById(R.id.textViewRoomId)
        val textViewIsOccupiedValue: TextView = itemView.findViewById(R.id.textViewIsOccupiedValue)
        val textViewMaxOccupancyValue: TextView = itemView.findViewById(R.id.textViewMaxOccupancyValue)
        val textViewCreatedAtValue: TextView = itemView.findViewById(R.id.textViewCreatedAtValue)

    }
}