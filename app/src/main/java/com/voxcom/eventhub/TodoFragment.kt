package com.voxcom.eventhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoFragment : Fragment() {

    private lateinit var todoRecycler: RecyclerView
    private lateinit var inputTask: EditText
    private lateinit var addButton: Button
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        todoRecycler = view.findViewById(R.id.todoRecycler)
        inputTask = view.findViewById(R.id.inputTask)
        addButton = view.findViewById(R.id.btnAddTask)

        adapter = TaskAdapter(tasks) { task ->
            tasks.remove(task)
            adapter.notifyDataSetChanged()
        }

        todoRecycler.layoutManager = LinearLayoutManager(requireContext())
        todoRecycler.adapter = adapter

        addButton.setOnClickListener {
            val title = inputTask.text.toString()
            if (title.isNotBlank()) {
                tasks.add(Task(title))
                adapter.notifyItemInserted(tasks.size - 1)
                inputTask.text.clear()
            }
        }

        return view
    }
}
