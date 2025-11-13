package com.voxcom.eventhub.utils

import android.content.Context
import com.voxcom.eventhub.models.User
import org.json.JSONArray
import org.json.JSONObject

class UserStorage(private val context: Context) {

    private val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        val userList = getUserList().toMutableList()
        userList.add(user)
        saveUserList(userList)
    }

    fun isEmailRegistered(email: String): Boolean {
        return getUserList().any { it.e == email }
    }

    fun validateUser(email: String, password: String): User? {
        return getUserList().find { it.e == email && it.p == password }
    }

    fun updateUser(user: User) {
        val userList = getUserList().toMutableList()
        val index = userList.indexOfFirst { it.e == user.e }
        if (index != -1) {
            userList[index] = user
            saveUserList(userList)
        }
    }

    fun getUserByEmail(email: String): User? {
        return getUserList().find { it.e == email }
    }

    private fun getUserList(): List<User> {
        val json = sharedPref.getString("users", "[]")
        val jsonArray = JSONArray(json)
        val userList = mutableListOf<User>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val selectedEventsJson = obj.optJSONArray("selectedEvents") ?: JSONArray()
            val tasksJson = obj.optJSONArray("tasks") ?: JSONArray()

            val selectedEvents = mutableListOf<String>()
            val tasks = mutableListOf<String>()

            for (j in 0 until selectedEventsJson.length()) {
                selectedEvents.add(selectedEventsJson.getString(j))
            }
            for (j in 0 until tasksJson.length()) {
                tasks.add(tasksJson.getString(j))
            }

            userList.add(
                User(
                    obj.getString("username"),
                    obj.getString("email"),
                    obj.getString("pass"),
                    obj.optBoolean("isLoggedIn", false),
                    selectedEvents,
                    tasks
                )
            )
        }
        return userList
    }

    private fun saveUserList(users: List<User>) {
        val jsonArray = JSONArray()
        for (user in users) {
            val obj = JSONObject()
            obj.put("username", user.u)
            obj.put("email", user.e)
            obj.put("pass", user.p)
            obj.put("isLoggedIn", user.isLoggedIn)
            obj.put("selectedEvents", JSONArray(user.selectedEvents))
            obj.put("tasks", JSONArray(user.tasks))
            jsonArray.put(obj)
        }
        sharedPref.edit().putString("users", jsonArray.toString()).apply()
    }

    fun getUsernameByEmail(email: String): String? {
        return getUserList().find { it.e == email }?.u
    }
    fun getAllUsers(): List<User> {
        return getUserList()
    }

}
