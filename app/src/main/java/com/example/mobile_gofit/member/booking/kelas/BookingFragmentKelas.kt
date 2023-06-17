package com.example.mobile_gofit.member.booking.kelas

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentBookingKelasBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.booking.kelas.ListBookingKelas
import com.example.mobile_gofit.retrofit.booking.kelas.ResponseListBookingKelas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class BookingFragmentKelas : Fragment() {
    private var _binding: FragmentBookingKelasBinding? = null
    private val binding get() = _binding!!
    private val listBookingKelas = ArrayList<ListBookingKelas>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingKelasBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        getDataBookingKelas()
    }

    private fun getDataBookingKelas() {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.getListBookingKelas().enqueue(object :Callback<ResponseListBookingKelas> {
                override fun onResponse(
                    call: Call<ResponseListBookingKelas>,
                    response: Response<ResponseListBookingKelas>
                ) {
                    if (response.isSuccessful){
                        listBookingKelas.clear()

                        response.body()?.let {listBookingKelas.addAll(it.data) }
                        val id = pref!!.getString("user", "").toString()
                        val adapter = BookingAdapterKelas(listBookingKelas, requireContext(), id) // lempar id
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