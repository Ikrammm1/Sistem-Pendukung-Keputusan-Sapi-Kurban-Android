package com.skadubai.sawsapi.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.skadubai.sawsapi.API.RetrofitClient
import com.skadubai.sawsapi.Login.LoginActivity
import com.skadubai.sawsapi.Model.ModelRespon
import com.skadubai.sawsapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var BtnRegister : Button
    lateinit var EtUsername : EditText
    lateinit var EtNama : EditText
    lateinit var EtPassword : EditText
    lateinit var EtKnfPassword : EditText
    lateinit var txtLogin : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        EtNama = findViewById(R.id.et_nama)
        EtUsername = findViewById(R.id.et_username)
        EtPassword = findViewById(R.id.et_password)
        EtKnfPassword = findViewById(R.id.et_knfpassword)
        BtnRegister = findViewById(R.id.btn_register)
        txtLogin= findViewById(R.id.ToLogin)

        txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        BtnRegister.setOnClickListener {
            when{
                EtNama.text.toString() == ""->{
                    EtNama.error = "Nama Tidak Boleh Kosong!"
                }
                EtUsername.text.toString() == "" ->{
                    EtUsername.error = "Username Tidak Boleh Kosong!"
                }
                EtPassword.text.toString() == "" -> {
                    EtPassword.error = "Password Tidak Boleh Kosong!"
                }
                EtKnfPassword.text.toString() == "" -> {
                    EtKnfPassword.error = "Konfirmasi Password Tidak Boleh Kosong!"
                }
                EtPassword.text.toString() != EtKnfPassword.text.toString() ->{
                    Toast.makeText(this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show()
                }
                else->{
                    Register()
                }

            }
        }
    }
    private fun Register(){
        RetrofitClient.instance.Register(
            EtNama.text.toString(),
            EtUsername.text.toString(),
            EtPassword.text.toString()
        ).enqueue(object  : Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                if(response.body()!!.status=="Ada"){
                    Toast.makeText(this@RegisterActivity, "Username Telah Terdaftar", Toast.LENGTH_SHORT).show()
                }else{
                    if (response.isSuccessful && response.body()!!.status =="Ok"){
                        Toast.makeText(this@RegisterActivity, "Register Berhasil, Silahkan Login", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this@RegisterActivity, "Terjadi Kesalahan. Ulangi", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Register", t.toString())
            }

        })
    }
}
