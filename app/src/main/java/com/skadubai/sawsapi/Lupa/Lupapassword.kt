package com.skadubai.sawsapi.Lupa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.skadubai.sawsapi.API.RetrofitClient
import com.skadubai.sawsapi.Model.ModelRespon
import com.skadubai.sawsapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Lupapassword : AppCompatActivity() {

    lateinit var EdtUsername : EditText
    lateinit var BtnGanti : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupapassword)

        EdtUsername = findViewById(R.id.et_username)
        BtnGanti = findViewById(R.id.btn_ganti)

        BtnGanti.setOnClickListener {
            CekUser()
        }
    }
    private fun CekUser(){
        RetrofitClient.instance.CekUser(EdtUsername.text.toString()).enqueue(object : Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if (response.isSuccessful && response.body()!!.status == "ok"){
                    Toast.makeText(this@Lupapassword, "Silahkan Masukkan Password Baru", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Lupapassword, GantiPassword::class.java)
                        .putExtra("Username", EdtUsername.text.toString()))
                }else if(response.body()!!.status == "nodata"){
                    Toast.makeText(this@Lupapassword, "Username Tidak Terdaftar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@Lupapassword, "Mohon Maaf sedang terjadi kesalahan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan Api Cek : ", t.toString())
            }

        })
    }
}
