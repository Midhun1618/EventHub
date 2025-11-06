package com.voxcom.eventhub

object DummyData {

    // Shared list of all events
    private val events = mutableListOf(
        Event(
            id = 1,
            name = "Tech Meetup 2025",
            date = "Nov 10, 2025",
            location = "Main Auditorium",
            imageResId = R.drawable.event_placeholder,
            isRSVP = false
        ),
        Event(
            id = 2,
            name = "AI Workshop",
            date = "Nov 14, 2025",
            location = "Innovation Hall",
            imageResId = R.drawable.event_placeholder,
            isRSVP = false
        ),
        Event(
            id = 3,
            name = "Hackathon 5.0",
            date = "Nov 18, 2025",
            location = "Computer Lab 301",
            imageResId = R.drawable.event_placeholder,
            isRSVP = false
        ),
        Event(
            id = 4,
            name = "Design Thinking Seminar",
            date = "Nov 22, 2025",
            location = "Hall B",
            imageResId = R.drawable.event_placeholder,
            isRSVP = false
        ),
        Event(
            id = 5,
            name = "Cloud Computing Summit",
            date = "Nov 26, 2025",
            location = "Conference Room 2",
            imageResId = R.drawable.event_placeholder,
            isRSVP = false
        )
    )

    fun getAllEvents(): MutableList<Event> = events

    fun getRSVPEvents(): MutableList<Event> {
        return events.filter { it.isRSVP }.toMutableList()
    }

    fun toggleRSVP(event: Event) {
        val index = events.indexOfFirst { it.id == event.id }
        if (index != -1) {
            events[index].isRSVP = !events[index].isRSVP
        }
    }
}
