package com.example.mobile_gofit.member.history.transaksi.kelas

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentListAktivasiBinding
import com.example.mobile_gofit.databinding.FragmentListKelasBinding

import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.history.member.transaksi.aktivasi.ListAktivasi
import com.example.mobile_gofit.retrofit.history.member.transaksi.aktivasi.ResponseListAktivasi
import com.example.mobile_gofit.retrofit.history.member.transaksi.kelas.ListDepositKelas
import com.example.mobile_gofit.retrofit.history.member.transaksi.kelas.ResponseListDepositKelas

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class ListFragmentKelas : Fragment() {
    private var _binding: FragmentListKelasBinding ? = null
    private val binding get() = _binding!!
    private val listData = ArrayList<ListDepositKelas>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListKelasBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "").toString()
        getListKelas(id)
    }

    private fun getListKelas(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.listDepositKelas(id).enqueue(object :Callback<ResponseListDepositKelas> {
                override fun onResponse(
                    call: Call<ResponseListDepositKelas>,
                    response: Response<ResponseListDepositKelas>
                ) {
                    if (response.isSuccessful){
                        listData.clear()

                        response.body()?.let {listData.addAll(it.data) }
                        val adapter = ListAdapterKelas(listData, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListDepositKelas>, t: Throwable) {
                }
            })
        }
    }
}