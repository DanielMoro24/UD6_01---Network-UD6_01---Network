package com.amarinag.demon06utadchat.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amarinag.demon06utadchat.databinding.FragmentUserslistBinding
import com.amarinag.demon06utadchat.network.RetrofitConfig
import com.amarinag.demon06utadchat.network.response.UserResponse
import com.amarinag.demon06utadchat.network.response.toMap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserListFragment : Fragment() {

    private var _binding: FragmentUserslistBinding? = null
    private val binding get() = _binding!!
    private val adapter: UserListAdapter = UserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserslistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUsers.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true)
        binding.rvUsers.adapter = this.adapter
        getMsg()
        binding.floatingActionButton.setOnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToAddUserFragment2()
            findNavController().navigate(action)
        }
    }



    private fun getMsg() {
        RetrofitConfig.SERVICE.getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    adapter.submitList(user?.users.toMap())
                } else {
                    Log.e("Network", "error en la conexion")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Network", "error en la conexion", t)


            }
        })
    }
}

