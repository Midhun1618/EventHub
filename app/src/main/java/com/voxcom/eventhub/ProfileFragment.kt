package com.voxcom.eventhub

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var userName: TextView
    private lateinit var userEmail: TextView
    private lateinit var editProfileBtn: ImageButton
    private lateinit var logoutBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileImage = view.findViewById(R.id.profileImage)
        userName = view.findViewById(R.id.userName)
        userEmail = view.findViewById(R.id.userEmail)
        editProfileBtn = view.findViewById(R.id.editProfileBtn)
        logoutBtn = view.findViewById(R.id.logoutBtn)

        userName.text = GlobalData.userName
        userEmail.text = GlobalData.eMail
        val prefs = requireContext().getSharedPreferences("UserPrefs", MODE_PRIVATE)

        editProfileBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Edit profile", Toast.LENGTH_SHORT).show()
        }

        logoutBtn.setOnClickListener {
            (requireActivity() as MainActivity).logout()
            prefs.edit().putBoolean("isLoggedIn", false).apply()

            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
