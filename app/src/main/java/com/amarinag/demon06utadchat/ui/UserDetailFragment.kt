package com.amarinag.demon06utadchat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amarinag.demon06utadchat.databinding.FragmentAdduserBinding
import com.amarinag.demon06utadchat.databinding.FragmentUserdetailBinding
import com.amarinag.demon06utadchat.network.RetrofitConfig
import com.amarinag.demon06utadchat.network.response.UserResponse
import com.amarinag.demon06utadchat.network.response.toMap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserdetailBinding? = null
    private val binding get() = _binding!!
    private val arg: UserDetailFragmentArgs by navArgs()

    private var userid: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserdetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            if (it != null) {
                userid = arg.userId
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserById()
        binding.btnDeleteuser.setOnClickListener {
            deleteUser()
        }
    }

    private fun deleteUser() {
        RetrofitConfig.SERVICE.deleteUser(userid).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val action = UserDetailFragmentDirections.
                    actionUserDetailFragment2ToUserListFragment()

                    findNavController().navigate(action)
                } else {
                    Log.e("Network", "error en la conexion")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Network", "error en la conexion", t)


            }
        })
    }

    private fun getUserById() {
        RetrofitConfig.SERVICE.getUserById(userid).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.users?.get(0)
                    if (user != null) {
                        binding.txtvNameDetail.text = user.name
                        binding.ttvSurnameDetail.text = user.Surname
                        binding.txtvUsernameDetail.text = user.Username
                        binding.txtvEmailDetail.text = user.email
                        binding.txtvAgeDetail.text = user.age.toString()
                        binding.txtvLevelDetail.text = user.level
                    }
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