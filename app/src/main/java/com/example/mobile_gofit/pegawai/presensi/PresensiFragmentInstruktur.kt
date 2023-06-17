package com.example.mobile_gofit.pegawai.presensi

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentPresensiInstrukturBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.presensi.instruktur.ListKelasHariIni
import com.example.mobile_gofit.retrofit.presensi.instruktur.ResponseListKelasHariIni
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class PresensiFragmentInstruktur : Fragment() {
    private var _binding: FragmentPresensiInstrukturBinding? = null
    private val binding get() = _binding!!
    private val listKelasHariIni = ArrayList<ListKelasHariIni>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPresensiInstrukturBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        getListKelasHariIni()
    }

    private fun getListKelasHariIni() {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.getListKelasHariIni().enqueue(object :Callback<ResponseListKelasHariIni> {
                override fun onResponse(
                    call: Call<ResponseListKelasHariIni>,
                    response: Response<ResponseListKelasHariIni>
                ) {
                    if (response.isSuccessful){
                        listKelasHariIni.clear()

                        response.body()?.let {listKelasHariIni.addAll(it.data) }
                        val adapter = PresensiAdapterInstruktur(listKelasHariIni, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListKelasHariIni>, t: Throwable) {
                }
            })
        }
    }
}