package com.example.mobile_gofit.instruktur.presensi

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
import com.example.mobile_gofit.databinding.FragmentPresensiMemberBinding
import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.presensi.instruktur.ListKelasHariIni
import com.example.mobile_gofit.retrofit.presensi.instruktur.ResponseListKelasHariIni
import com.example.mobile_gofit.retrofit.presensi.member.ListKelasMember
import com.example.mobile_gofit.retrofit.presensi.member.ResponseListKelasMember
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class PresensiFragmentMember : Fragment() {
    private var _binding: FragmentPresensiMemberBinding? = null
    private val binding get() = _binding!!
    private val listMember = ArrayList<ListKelasMember>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPresensiMemberBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "").toString()
        getListKelasHariIni(id)
    }

    private fun getListKelasHariIni(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.listKelasMember(id).enqueue(object :Callback<ResponseListKelasMember> {
                override fun onResponse(
                    call: Call<ResponseListKelasMember>,
                    response: Response<ResponseListKelasMember>
                ) {
                    if (response.isSuccessful){
                        listMember.clear()

                        response.body()?.let {listMember.addAll(it.data) }
                        val adapter = PresensiAdapterMember(listMember, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListKelasMember>, t: Throwable) {
                }
            })
        }
    }
}