package com.skadubai.sawsapi.Hasil

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.skadubai.sawsapi.API.RetrofitClient
import com.skadubai.sawsapi.Beranda.BerandaActivity
import com.skadubai.sawsapi.Datasapi.DatasapiActivity
import com.skadubai.sawsapi.Model.ModelRanking
import com.skadubai.sawsapi.Model.ModelRespon
import com.skadubai.sawsapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilActivity : AppCompatActivity() {
    lateinit var Adapter : AdapterHasil
    lateinit var ListItem : RecyclerView
    private lateinit var profil : SharedPreferences
    lateinit var  Id : String
    lateinit var BtnBack : ImageView
    lateinit var HapusAll : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)
        BtnBack = findViewById(R.id.btn_Back)
        HapusAll = findViewById(R.id.btn_HapusAll)
        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)
        Id = profil.getString("id_user", null)!!
        setUpList()

        BtnBack.setOnClickListener {
            startActivity(Intent(this, BerandaActivity::class.java))
        }
        HapusAll.setOnClickListener {
            HapusAll()
        }
    }
    override fun onStart(){
        super.onStart()
        getData()

    }
    private fun getData(){
        RetrofitClient.instance.Ranking(Id).enqueue(object : Callback<ModelRanking>{
            override fun onResponse(call: Call<ModelRanking>, response: Response<ModelRanking>) {
                if (response.isSuccessful){
                    val listData = response.body()!!.Ranking
                    if (listData.size != 0){
                        HapusAll.visibility = View.VISIBLE
                    }else{
                        HapusAll.visibility = View.GONE
                    }
                    listData.forEach {
                        Adapter.setData(listData)
                    }

                }
            }

            override fun onFailure(call: Call<ModelRanking>, t: Throwable) {
                Toast.makeText(this@HasilActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API Ranking", t.toString())
            }

        })
    }
    private fun setUpList(){
        ListItem = findViewById(R.id.list_hasil)
        Adapter = AdapterHasil(arrayListOf(), object : AdapterHasil.OnAdapterlistener{
            override fun onClick(detail: ModelRanking.dataRanking) {
                startActivity(Intent(this@HasilActivity, DetailHasil::class.java)
                    .putExtra("Alternatif", detail.alternatif)
                    .putExtra("Berat", detail.berat)
                    .putExtra("Kecacatan", detail.kecacatan)
                    .putExtra("Perilaku", detail.perilaku)
                    .putExtra("Umur", detail.umur)
                    .putExtra("Jk", detail.jenis_kelamin)
                    .putExtra("Nilai", detail.nilai)
                    .putExtra("Ranking", detail.ranking)
                    .putExtra("Kesimpulan", detail.kesimpulan)
                )
            }

            override fun onDelete(detail: ModelRanking.dataRanking) {
                var alertDialog = AlertDialog.Builder(this@HasilActivity)
                    .setTitle("Apakah Anda Yakin Ingin Menghapus Data Sapi?")
                    .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                        RetrofitClient.instance.HapusSapi(detail.id_datasapi).enqueue(object : Callback<ModelRespon>{
                            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                                if (response.isSuccessful){
                                    Toast.makeText(this@HasilActivity, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                                    getData()

                                }
                            }

                            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                                Toast.makeText(this@HasilActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                                Log.e("Kesalahan API Hapus", t.toString())
                            }

                        })

                    })
                    .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                    .show()
            }

        })
        ListItem.adapter = Adapter
    }
    private fun HapusAll(){
        RetrofitClient.instance.HapusAll(Id).enqueue(object : Callback<ModelRespon>{
            override fun onResponse(call: Call<ModelRespon>, response: Response<ModelRespon>) {
                Toast.makeText(this@HasilActivity, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                getData()
            }

            override fun onFailure(call: Call<ModelRespon>, t: Throwable) {
                Toast.makeText(this@HasilActivity, "Maaf Sistem Sedang Gangguan", Toast.LENGTH_SHORT).show()
                Log.e("Kesalahan API HapusAll", t.toString())
            }

        })
    }
}
