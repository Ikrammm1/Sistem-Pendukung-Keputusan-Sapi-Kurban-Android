package com.skadubai.sawsapi.About

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.skadubai.sawsapi.API.RetrofitClient
import com.skadubai.sawsapi.Model.ModelAbout
import com.skadubai.sawsapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AboutActivity : AppCompatActivity() {
    lateinit var txtTentang : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        txtTentang = findViewById(R.id.txtTentang)
        RetrofitClient.instance.About().enqueue(object :Callback<ModelAbout>{
            override fun onResponse(call: Call<ModelAbout>, response: Response<ModelAbout>) {
                txtTentang.text = response.body()!!.about.toString()
            }

            override fun onFailure(call: Call<ModelAbout>, t: Throwable) {
                Toast.makeText(this@AboutActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API About", t.toString())
            }

        })
    }

}
