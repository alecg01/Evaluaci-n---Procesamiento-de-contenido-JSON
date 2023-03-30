package com.example.evaluacin.Adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.evaluacin.databinding.FragmentMostrar60Binding
import com.example.evaluacin.Adapter.Adapter.UserAdapter
import com.example.evaluacin.Adapter.Adapter.UserData

class Mostrar60Fragment : Fragment() {
    private var _binding: FragmentMostrar60Binding? = null
    private val binding get() = _binding!!
    val userList = arrayListOf<UserData>()
    val uri = "http://192.168.1.15/Evaluación/mostrarCoordinador60.php"
    var recycleView: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMostrar60Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView = binding.recyclerview

        val reqQueue: RequestQueue = Volley.newRequestQueue(getActivity())
        val request = JsonObjectRequest(Request.Method.GET,uri,null, { res ->
            val jsonArray = res.getJSONArray("data")

            for (i in 0 until jsonArray.length()){
                val jsonObj = jsonArray.getJSONObject(i)
                Log.d("Volley Sample",jsonObj.toString())
                val user = UserData(
                    jsonObj.getString("idC"),
                    jsonObj.getString("nombres"),
                    jsonObj.getString("apellidos"),
                    jsonObj.getString("fechaNac"),
                    jsonObj.getString("titulo"),
                    jsonObj.getString("email"),
                    jsonObj.getString("facultad")
                )
                Log.d("Good", userList.toString())
                userList.add(user)
            }
            println(userList.toString())

            recycleView?.layoutManager = LinearLayoutManager(getActivity())
            recycleView?.adapter = UserAdapter(userList)

        },{err ->
            Log.d("Fail", err.message.toString())
        })

        reqQueue.add(request)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}