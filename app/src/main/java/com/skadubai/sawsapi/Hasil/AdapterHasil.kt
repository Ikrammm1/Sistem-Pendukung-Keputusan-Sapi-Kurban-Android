package com.skadubai.sawsapi.Hasil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skadubai.sawsapi.Model.ModelRanking
import com.skadubai.sawsapi.R

class AdapterHasil(
    val Ranking : ArrayList<ModelRanking.dataRanking>,
    val listener : OnAdapterlistener
) : RecyclerView.Adapter<AdapterHasil.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val No = view.findViewById<TextView>(R.id.txtNo)
        val Alt = view.findViewById<TextView>(R.id.txtAlt)
        val Nilai = view.findViewById<TextView>(R.id.txtNilai)
        val Rank = view.findViewById<TextView>(R.id.txtRank)
        val BtnHapus = view.findViewById<ImageView>(R.id.btnHapus)

    }
    interface  OnAdapterlistener {
        fun onClick(detail: ModelRanking.dataRanking)
        fun onDelete(detail: ModelRanking.dataRanking)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_hasil,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = Ranking[position]
        val nomor = position+1
        holder.Alt.text = data.alternatif
        holder.Nilai.text = data.nilai
        holder.Rank.text = data.ranking
        holder.No.text = nomor.toString()
        holder.itemView.setOnClickListener {
            listener.onClick(data)
        }
        holder.BtnHapus.setOnClickListener {
            listener.onDelete(data)
        }

    }

    override fun getItemCount()= Ranking.size

    fun setData(data : List<ModelRanking.dataRanking>){
        Ranking.clear()
        Ranking.addAll(data)
        notifyDataSetChanged()
    }
}
