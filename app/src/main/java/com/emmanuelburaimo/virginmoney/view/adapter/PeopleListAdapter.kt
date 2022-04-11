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
import com.emmanuelburaimo.virginmoney.util.downloadFromUrl
import com.emmanuelburaimo.virginmoney.util.placeholderProgressBar

class PeopleListAdapter (private var peopleList: List<PersonaModel>) :
    RecyclerView.Adapter<PeopleListAdapter.MyViewHolder>() {

    fun updatePeopleList(peopleList: List<PersonaModel>) {
        this.peopleList = peopleList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_persona_model, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        peopleList.let {

            if (it.isNotEmpty()) {
                holder.textViewFirstName.text = it[position].firstName
                holder.textViewLastName.text = it[position].lastName
                holder.textViewJobtitle.text=it[position].jobtitle
                holder.imageViewPersona.downloadFromUrl(
                    it[position].avatar,
                    placeholderProgressBar(holder.itemView.context)
                )
            }

            holder.itemView.setOnClickListener {
                if (peopleList.isNotEmpty()) {

                    var bundle= Bundle()
                    bundle.putParcelable("PeopleModel", peopleList[position])

                    Navigation.findNavController(holder.itemView)
                        .navigate(R.id.action_peopleListFragment_to_peopleDetailsFragment,bundle)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (peopleList.isEmpty()) {
            1
        } else {
            peopleList.size
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPersona: ImageView = itemView.findViewById(R.id.imageViewPersona)
        val textViewFirstName: TextView = itemView.findViewById(R.id.textViewFirstName)
        val textViewJobtitle: TextView = itemView.findViewById(R.id.textViewJobtitle)
        val textViewLastName: TextView = itemView.findViewById(R.id.textViewLastName)
    }
}