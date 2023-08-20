package com.skadubai.sawsapi.Datasapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skadubai.sawsapi.Hasil.AdapterHasil
import com.skadubai.sawsapi.Model.ModelRanking
import com.skadubai.sawsapi.Model.ModelRespon
import com.skadubai.sawsapi.Model.ModelSapi
import com.skadubai.sawsapi.R

class AdapterSapi(
    val Datasapi : ArrayList<ModelSapi.dataSapi>,
    val listener : OnAdapterlistener
) : RecyclerView.Adapter<AdapterSapi.ViewHolder>()
 {
     class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
         val No = view.findViewById<TextView>(R.id.txtNo)
         val Nama = view.findViewById<TextView>(R.id.txtNama)
         val C1 = view.findViewById<TextView>(R.id.txtC1)
         val C2 = view.findViewById<TextView>(R.id.txtC2)
         val C3 = view.findViewById<TextView>(R.id.txtC3)
         val C4 = view.findViewById<TextView>(R.id.txtC4)
         val C5 = view.findViewById<TextView>(R.id.txtC5)
         val BtnHapus = view.findViewById<ImageView>(R.id.btnHapus)
     }
     interface OnAdapterlistener{
         fun onClick(detail: ModelSapi.dataSapi)
         fun onDelete(detail : ModelSapi.dataSapi)
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= AdapterSapi.ViewHolder(
         LayoutInflater.from(parent.context)
             .inflate(R.layout.adapter_sapi, parent, false)
     )

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val data = Datasapi[position]
         val nomor = position+1
         holder.Nama.text = data.alternatif
         holder.C1.text = data.berat
         holder.C2.text = data.kecacatan
         holder.C3.text = data.perilaku
         holder.C4.text = data.umur
         holder.C5.text = data.jenis_kelamin

         holder.No.text = nomor.toString()
         holder.itemView.setOnClickListener {
             listener.onClick(data)
         }
         holder.BtnHapus.setOnClickListener {
             listener.onDelete(data)
         }
     }

     override fun getItemCount() = Datasapi.size

     fun setData(data : List<ModelSapi.dataSapi>){
         Datasapi.clear()
         Datasapi.addAll(data)
         notifyDataSetChanged()
     }


 }
