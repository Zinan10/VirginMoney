package com.emmanuelburaimo.virginmoney.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.emmanuelburaimo.virginmoney.R
import com.emmanuelburaimo.virginmoney.view.adapter.PeopleListAdapter
import com.emmanuelburaimo.virginmoney.viewModel.PeopleViewModel
import kotlinx.android.synthetic.main.fragment_people_list.*

class PeopleListFragment : Fragment() {

    private val viewModel: PeopleViewModel by viewModels()
    private val peopleAdapter = PeopleListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewPeople.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewPeople.adapter = peopleAdapter

        viewModel.refreshData()
        observePeople()
    }


    private fun observePeople() {

        viewModel.people.observe(viewLifecycleOwner) { people ->
            people?.let {

                peopleAdapter.updatePeopleList(people)

            }
        }

        viewModel.peopleError.observe(viewLifecycleOwner) { error ->

            error?.let {
                if (it) {
                    textViewErrorMessage.visibility = View.VISIBLE
                } else {
                    textViewErrorMessage.visibility = View.GONE
                }

            }
        }

        viewModel.peopleLoading.observe(viewLifecycleOwner) { loading ->

            loading?.let {
                if (it) {
                    progressBarLoading.visibility = View.VISIBLE
                    textViewErrorMessage.visibility = View.GONE
                    recyclerViewPeople.visibility = View.GONE
                } else {
                    progressBarLoading.visibility = View.GONE
                    recyclerViewPeople.visibility = View.VISIBLE

                }
            }
        }
    }


}