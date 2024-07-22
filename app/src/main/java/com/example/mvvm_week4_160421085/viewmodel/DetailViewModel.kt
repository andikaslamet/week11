package com.example.mvvm_week4_160421085.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mvvm_week4_160421085.model.Student
import com.google.gson.Gson
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var queue: RequestQueue? = null

    val TAG = "volleyTag"

    fun fetch(studentId: String) {
        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener<JSONObject> { response ->
                try {
                    val gson = Gson()
                    val student = gson.fromJson(response.toString(), Student::class.java)
                    studentLD.value = student
                    loadingLD.value = false
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing JSON: ${e.localizedMessage}")
                    studentLoadErrorLD.value = true
                    loadingLD.value = false
                }
            },
            Response.ErrorListener { error ->
                Log.e(TAG, "Volley error: $error")
                studentLoadErrorLD.value = true
                loadingLD.value = false
            })

        jsonObjectRequest.tag = TAG
        queue?.add(jsonObjectRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
