package com.skadubai.sawsapi.Kriteria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.skadubai.sawsapi.API.RetrofitClient
import com.skadubai.sawsapi.Model.ModelKriteria
import com.skadubai.sawsapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KriteriaActivity : AppCompatActivity() {
    lateinit var Adapter : AdapterKriteria
    lateinit var listItem : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kriteria)
        setUpList()
    }

    override fun onStart(){
        super.onStart()
        getData()
    }
    private fun getData(){
        RetrofitClient.instance.Kriteria().enqueue(object : Callback<ModelKriteria>{
            override fun onResponse(
                call: Call<ModelKriteria>,
                response: Response<ModelKriteria>
            ) {
                if (response.isSuccessful){
                    val ListData = response.body()!!.Kriteria
                    ListData.forEach {
                        Adapter.setData(ListData)
                    }
                } else{
                    Toast.makeText(this@KriteriaActivity, "Maaf Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelKriteria>, t: Throwable) {
                Toast.makeText(this@KriteriaActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Kriteria : ", t.toString())
            }

        })
    }
    private fun setUpList(){
        listItem = findViewById(R.id.listKriteria)
        Adapter = AdapterKriteria(arrayListOf())
        listItem.adapter = Adapter
    }
}
