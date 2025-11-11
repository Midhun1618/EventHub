package com.voxcom.eventhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScheduleFragment : Fragment() {

    private lateinit var scheduleRecycler: RecyclerView
    private lateinit var adapter: EventAdapter
    private var rsvpEvents = mutableListOf<Event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        scheduleRecycler = view.findViewById(R.id.scheduleRecycler)
        scheduleRecycler.layoutManager = LinearLayoutManager(requireContext())


        rsvpEvents = DummyData.getRSVPEvents()

        adapter = EventAdapter(rsvpEvents) { event ->
            DummyData.toggleRSVP(event)
            rsvpEvents = DummyData.getRSVPEvents()
            adapter.updateData(rsvpEvents)
        }

        scheduleRecycler.adapter = adapter

        return view
    }

}
