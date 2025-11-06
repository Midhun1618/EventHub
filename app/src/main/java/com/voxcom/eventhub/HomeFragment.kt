package com.voxcom.eventhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: EventAdapter
    private var events = mutableListOf<Event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recycler = view.findViewById(R.id.eventRecycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        events = DummyData.getAllEvents()
        adapter = EventAdapter(events) { event ->
            DummyData.toggleRSVP(event)
            adapter.notifyDataSetChanged()
            Toast.makeText(
                requireContext(),
                if (event.isRSVP) "RSVP’d to ${event.name}" else "Cancelled RSVP for ${event.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        recycler.adapter = adapter
        return view
    }
}
