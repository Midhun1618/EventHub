
package com.voxcom.eventhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(
    private val events: MutableList<Event>,
    private val onRSVPClick: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.eventName)
        val date: TextView = view.findViewById(R.id.eventDate)
        val location: TextView = view.findViewById(R.id.eventLocation)
        val rsvpButton: Button = view.findViewById(R.id.rsvpButton)
        val image: ImageView = view.findViewById(R.id.eventImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.name.text = event.name
        holder.date.text = event.date
        holder.location.text = event.location
        holder.image.setImageResource(event.imageResId)
        holder.rsvpButton.text = if (event.isRSVP) "Cancel" else "Join"

        holder.rsvpButton.setOnClickListener {
            onRSVPClick(event)
        }
    }
    fun updateData(newEvents: MutableList<Event>) {
        events.clear()
        events.addAll(newEvents)
        notifyDataSetChanged()
    }

}
