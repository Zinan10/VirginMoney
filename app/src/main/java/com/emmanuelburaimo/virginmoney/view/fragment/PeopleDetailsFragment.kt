package com.emmanuelburaimo.virginmoney.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emmanuelburaimo.virginmoney.R
import com.emmanuelburaimo.virginmoney.repository.model.PersonaModel
import com.emmanuelburaimo.virginmoney.util.downloadFromUrl
import com.emmanuelburaimo.virginmoney.util.placeholderProgressBar
import kotlinx.android.synthetic.main.fragment_people_details.*

class PeopleDetailsFragment : Fragment() {

    lateinit var peopleModel: PersonaModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            peopleModel = requireArguments().getParcelable<PersonaModel>("PeopleModel")!!
            Log.e("", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewData(peopleModel)
    }

    fun bindViewData(peopleModel: PersonaModel) {


        imageViewProfile.downloadFromUrl(
            peopleModel.avatar,
            placeholderProgressBar(requireContext())
        )
        imageViewfirstName.text = peopleModel.firstName
        imageViewlastName.text = peopleModel.lastName
        imageViewjobTitle.text = peopleModel.jobtitle
        imageViewemail.text = peopleModel.email


    }

}