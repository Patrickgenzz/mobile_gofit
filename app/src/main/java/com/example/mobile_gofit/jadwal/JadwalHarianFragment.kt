package com.example.mobile_gofit.jadwal

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentJadwalHarianBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.jadwal.ListJadwalHarian
import com.example.mobile_gofit.retrofit.jadwal.ResponseListJadwalHarian
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class JadwalHarianFragment : Fragment() {
    private var _binding: FragmentJadwalHarianBinding? = null
    private val binding get() = _binding!!
    private val listJadwalHarian = ArrayList<ListJadwalHarian>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJadwalHarianBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        getDataJadwalHarian()
    }

    private fun getDataJadwalHarian() {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.getListJadwalHarian().enqueue(object :Callback<ResponseListJadwalHarian> {
                override fun onResponse(
                    call: Call<ResponseListJadwalHarian>,
                    response: Response<ResponseListJadwalHarian>
                ) {
                    if (response.isSuccessful){
                        listJadwalHarian.clear()
                        response.body()?.let {listJadwalHarian.addAll(it.data) }
                        val adapter = JadwalHarianAdapter(listJadwalHarian, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListJadwalHarian>, t: Throwable) {
                }
            })
        }
    }
}