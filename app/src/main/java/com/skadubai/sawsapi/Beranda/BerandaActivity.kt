package com.skadubai.sawsapi.Beranda

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.skadubai.sawsapi.About.AboutActivity
import com.skadubai.sawsapi.Datasapi.DatasapiActivity
import com.skadubai.sawsapi.Hasil.HasilActivity
import com.skadubai.sawsapi.InputSapi.InputActivity
import com.skadubai.sawsapi.Kriteria.KriteriaActivity
import com.skadubai.sawsapi.Login.LoginActivity
import com.skadubai.sawsapi.Profile.Profile
import com.skadubai.sawsapi.R
import kotlinx.android.synthetic.main.activity_beranda.*

class BerandaActivity : AppCompatActivity() {
    lateinit var Prof : ImageView
    lateinit var txtNama : TextView
    lateinit var BtnLogout : ImageView
    lateinit var BtnInput : CardView
    lateinit var BtnKriteria : CardView
    lateinit var BtnHasil : CardView
    lateinit var BtnAbout : CardView
    private lateinit var profil : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        profil = getSharedPreferences("Login_Session", MODE_PRIVATE)

        txtNama = findViewById(R.id.txtnama)
        BtnLogout = findViewById(R.id.btn_logout)
        BtnInput = findViewById(R.id.BtnInput)
        BtnKriteria = findViewById(R.id.BtnKriteria)
        BtnHasil = findViewById(R.id.BtnHasil)
        BtnAbout = findViewById(R.id.BtnAbout)
        Prof = findViewById(R.id.prof)

        Prof.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }

        txtNama.text = "Hi ${profil.getString("nama_user", null)}"

        BtnLogout.setOnClickListener {

            var alertDialog = AlertDialog.Builder(this)
                .setTitle("Apakah Anda Yakin Ingin Keluar?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    //menghapus session
                    profil.edit().clear().commit()

                    val kelogin = Intent (this@BerandaActivity, LoginActivity::class.java)
                    startActivity(kelogin)

                })
                .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
                .show()

        }

        BtnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        BtnKriteria.setOnClickListener {
            startActivity(Intent(this, KriteriaActivity::class.java))
        }
        BtnInput.setOnClickListener {
            startActivity(Intent(this, DatasapiActivity::class.java))
        }
        BtnHasil.setOnClickListener {
            startActivity(Intent(this, HasilActivity::class.java))
        }

    }
}
