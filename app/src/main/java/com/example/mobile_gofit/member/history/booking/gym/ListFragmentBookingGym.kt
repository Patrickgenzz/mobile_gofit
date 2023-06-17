package com.example.mobile_gofit.member.history.booking.gym

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentListBookingGymBinding

import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.history.member.booking.gym.ListBookingGym
import com.example.mobile_gofit.retrofit.history.member.booking.gym.ResponseListBookingGym

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class ListFragmentBookingGym : Fragment() {
    private var _binding: FragmentListBookingGymBinding? = null
    private val binding get() = _binding!!
    private val listBooking = ArrayList<ListBookingGym>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBookingGymBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "").toString()
        getListBookingGym(id)
    }

    private fun getListBookingGym(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.listBookingGym(id).enqueue(object :Callback<ResponseListBookingGym> {
                override fun onResponse(
                    call: Call<ResponseListBookingGym>,
                    response: Response<ResponseListBookingGym>
                ) {
                    if (response.isSuccessful){
                        listBooking.clear()

                        response.body()?.let {listBooking.addAll(it.data) }
                        val adapter = ListAdapterBookingGym(listBooking, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListBookingGym>, t: Throwable) {
                }
            })
        }
    }
}