package com.emmanuelburaimo.virginmoney.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.emmanuelburaimo.virginmoney.R
import com.emmanuelburaimo.virginmoney.view.adapter.RoomListAdapter
import com.emmanuelburaimo.virginmoney.viewModel.RoomsViewModel
import kotlinx.android.synthetic.main.fragment_people_list.progressBarLoading
import kotlinx.android.synthetic.main.fragment_people_list.textViewErrorMessage
import kotlinx.android.synthetic.main.fragment_room_list.*

class RoomListFragment : Fragment() {

    private val viewModel: RoomsViewModel by viewModels()
    private val roomAdapter = RoomListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewRoom.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewRoom.adapter = roomAdapter

        viewModel.refreshData()
        observeRoom()
    }


    private fun observeRoom() {

        viewModel.rooms.observe(viewLifecycleOwner) { rooms ->
            rooms?.let {

                roomAdapter.updateRoomList(rooms)

            }
        }

        viewModel.roomsError.observe(viewLifecycleOwner) { error ->

            error?.let {
                if (it) {
                    textViewErrorMessage.visibility = View.VISIBLE
                } else {
                    textViewErrorMessage.visibility = View.GONE
                }

            }
        }

        viewModel.roomsLoading.observe(viewLifecycleOwner) { loading ->

            loading?.let {
                if (it) {
                    progressBarLoading.visibility = View.VISIBLE
                    textViewErrorMessage.visibility = View.GONE
                    recyclerViewRoom.visibility = View.GONE
                } else {
                    progressBarLoading.visibility = View.GONE
                    recyclerViewRoom.visibility = View.VISIBLE

                }
            }
        }
    }

}