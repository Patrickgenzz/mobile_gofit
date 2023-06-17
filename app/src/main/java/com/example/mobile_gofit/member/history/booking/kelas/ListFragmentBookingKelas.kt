package com.example.mobile_gofit.member.history.booking.kelas

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentListBookingKelasBinding

import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.history.member.booking.kelas.ListBookingKelas
import com.example.mobile_gofit.retrofit.history.member.booking.kelas.ResponseListBookingKelas

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class ListFragmentBookingKelas : Fragment() {
    private var _binding: FragmentListBookingKelasBinding? = null
    private val binding get() = _binding!!
    private val listBooking = ArrayList<ListBookingKelas>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBookingKelasBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "").toString()
        getListBookingKelas(id)
    }

    private fun getListBookingKelas(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.listBookingKelas(id).enqueue(object :Callback<ResponseListBookingKelas> {
                override fun onResponse(
                    call: Call<ResponseListBookingKelas>,
                    response: Response<ResponseListBookingKelas>
                ) {
                    if (response.isSuccessful){
                        listBooking.clear()

                        response.body()?.let {listBooking.addAll(it.data) }
                        val adapter = ListAdapterBookingKelas(listBooking, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListBookingKelas>, t: Throwable) {
                }
            })
        }
    }
}