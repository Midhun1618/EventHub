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
        return getUserList().any { it.e== email }
    }

    fun validateUser(email: String, password: String): User? {
        return getUserList().find { it.e == email && it.p== password }
    }

    private fun getUserList(): List<User> {
        val json = sharedPref.getString("users", "[]")
        val jsonArray = JSONArray(json)
        val userList = mutableListOf<User>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            userList.add(
                User(
                    obj.getString("username"),
                    obj.getString("email"),
                    obj.getString("pass")
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
            jsonArray.put(obj)
        }
        sharedPref.edit().putString("users", jsonArray.toString()).apply()
    }
    fun getUsernameByEmail(email: String): String? {
        return getUserList().find { it.e == email }?.u
    }

}
