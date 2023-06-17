package com.example.mobile_gofit.instruktur.history

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentListPresensiInstrukturBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.history.instruktur.ListPresensiInstruktur
import com.example.mobile_gofit.retrofit.history.instruktur.ResponseListPresensiInstruktur

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class ListPresensiFragmentInstruktur : Fragment() {
    private var _binding: FragmentListPresensiInstrukturBinding? = null
    private val binding get() = _binding!!
    private val listPresensi = ArrayList<ListPresensiInstruktur>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPresensiInstrukturBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "").toString()
        getListPresensiInstrukutur(id)
    }

    private fun getListPresensiInstrukutur(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.listPresensiInstruktur(id).enqueue(object :Callback<ResponseListPresensiInstruktur> {
                override fun onResponse(
                    call: Call<ResponseListPresensiInstruktur>,
                    response: Response<ResponseListPresensiInstruktur>
                ) {
                    if (response.isSuccessful){
                        listPresensi.clear()

                        response.body()?.let {listPresensi.addAll(it.data) }
                        val adapter = ListPresensiAdapterInstruktur(listPresensi, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListPresensiInstruktur>, t: Throwable) {
                }
            })
        }
    }
}