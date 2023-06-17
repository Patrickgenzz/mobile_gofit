package com.example.mobile_gofit.member.history.transaksi.uang

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_gofit.databinding.FragmentListUangBinding

import com.example.mobile_gofit.retrofit.RClient
import com.example.mobile_gofit.retrofit.history.member.transaksi.uang.ListDepositUang
import com.example.mobile_gofit.retrofit.history.member.transaksi.uang.ResponseListDepositUang

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class ListFragmentUang : Fragment() {
    private var _binding: FragmentListUangBinding ? = null
    private val binding get() = _binding!!
    private val listData = ArrayList<ListDepositUang>()
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListUangBinding.inflate(inflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref!!.getString("user", "").toString()
        getListDepositUang(id)
    }

    private fun getListDepositUang(id: String) {
        with(binding){
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)

            progressBar.visibility
            RClient.instances.listDepositUang(id).enqueue(object :Callback<ResponseListDepositUang> {
                override fun onResponse(
                    call: Call<ResponseListDepositUang>,
                    response: Response<ResponseListDepositUang>
                ) {
                    if (response.isSuccessful){
                        listData.clear()

                        response.body()?.let {listData.addAll(it.data) }
                        val adapter = ListAdapterUang(listData, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }

                override fun onFailure(call: Call<ResponseListDepositUang>, t: Throwable) {
                }
            })
        }
    }
}