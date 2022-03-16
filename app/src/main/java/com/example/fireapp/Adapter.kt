package com.example.fireapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter (val mCtx: Context, val layoutResId: Int, val list: List<User> )
    : ArrayAdapter<User>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textKelas = view.findViewById<TextView>(R.id.textKelas)
        val textSekolah = view.findViewById<TextView>(R.id.textSekolah)
        val textNis = view.findViewById<TextView>(R.id.textNis)
        val textNisn = view.findViewById<TextView>(R.id.textNisn)

        val user = list[position]

        textNama.text = user.name
        textKelas.text = user.kelas
        textSekolah.text = user.sekolah
        textNis.text = user.nis
        textNisn.text = user.nisn

        return view

    }
}