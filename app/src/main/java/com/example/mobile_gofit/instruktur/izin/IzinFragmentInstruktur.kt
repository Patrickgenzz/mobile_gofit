package com.example.mobile_gofit.instruktur.izin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentIzinInstrukturBinding
import com.example.mobile_gofit.retrofit.instruktur.IzinInstruktur
import com.example.mobile_gofit.retrofit.instruktur.ResponseIzinInstruktur
import com.example.mobile_gofit.retrofit.RClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class IzinFragmentInstruktur : Fragment() {
    private var _binding: FragmentIzinInstrukturBinding? = null
    private val binding get() = _binding!!
    private val listIzin = ArrayList<IzinInstruktur>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIzinInstrukturBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "")
        id?.let { getDataIzinInstruktur(it) }
    }

    private fun getDataIzinInstruktur(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.getIzinInstrukturById(id).enqueue(object :Callback<ResponseIzinInstruktur> {
                override fun onResponse(
                    call: Call<ResponseIzinInstruktur>,
                    response: Response<ResponseIzinInstruktur>
                ) {
                    if (response.isSuccessful){
                        listIzin.clear()

                        response.body()?.let {listIzin.addAll(it.data) }
                        val adapter = IzinAdapterInstruktur(listIzin, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseIzinInstruktur>, t: Throwable) {
                }
            })
        }
    }
}