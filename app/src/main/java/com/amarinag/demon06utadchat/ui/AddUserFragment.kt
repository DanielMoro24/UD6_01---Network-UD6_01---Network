package com.amarinag.demon06utadchat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amarinag.demon06utadchat.databinding.FragmentAdduserBinding
import com.amarinag.demon06utadchat.databinding.FragmentUserslistBinding
import com.amarinag.demon06utadchat.network.RetrofitConfig
import com.amarinag.demon06utadchat.network.request.UserRequest
import com.amarinag.demon06utadchat.network.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddUserFragment : Fragment() {

    private var _binding: FragmentAdduserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdduserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdduser.setOnClickListener {
            postUser()
        }
    }

    private fun postUser() {
        RetrofitConfig.SERVICE.postUser(UserRequest(binding.txtfUsername.text.toString(),
            binding.txtfName.text.toString(), binding.txtfSurname.text.toString(),
            binding.txtfEmail.text.toString(), binding.txtfAge.text.toString().toInt(),
            binding.txtfLevel.text.toString()))
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        Log.e("Network", "Todo correcto")
                        binding.txtvAviso.text = "Añadido correctamente"
                    } else {
                        Log.e("Network", "error en la conexion")
                        binding.txtvAviso.text = "Ups, algo raro ha pasasado"
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("Network", "error en la conexion", t)
                    binding.txtvAviso.text = "Ups, algo raro ha pasasado"
                }
            })
    }

}