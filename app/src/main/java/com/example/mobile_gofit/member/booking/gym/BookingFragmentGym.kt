package com.example.mobile_gofit.member.booking.gym

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentBookingGymBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.booking.gym.ListBookingGym
import com.example.mobile_gofit.retrofit.booking.gym.ResponseListBookingGym
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class BookingFragmentGym : Fragment() {
    private var _binding: FragmentBookingGymBinding? = null
    private val binding get() = _binding!!
    private val listGym = ArrayList<ListBookingGym>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingGymBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "").toString()
        getDataBookingGym(id)
    }

    private fun getDataBookingGym(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.getListBookingGym(id).enqueue(object :Callback<ResponseListBookingGym> {
                override fun onResponse(
                    call: Call<ResponseListBookingGym>,
                    response: Response<ResponseListBookingGym>
                ) {
                    if (response.isSuccessful){
                        listGym.clear()

                        response.body()?.let {listGym.addAll(it.data) }
                        val adapter = BookingAdapterGym(listGym, requireContext())
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