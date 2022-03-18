package com.example.fireapp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.google.firebase.database.FirebaseDatabase

class Adapter (val mCtx: Context, val layoutResId: Int, val list: List<User> )
    : ArrayAdapter<User>(mCtx,layoutResId,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textKelas = view.findViewById<TextView>(R.id.textKelas)
        val textSekolah = view.findViewById<TextView>(R.id.textSekolah)
        val textNis = view.findViewById<TextView>(R.id.textNis)
        val textNisn = view.findViewById<TextView>(R.id.textNisn)
        val update = view.findViewById<TextView>(R.id.btnUpdate)
        val delete = view.findViewById<TextView>(R.id.btnDelete)

        val user = list[position]

        textNama.text = user.name
        textKelas.text = user.kelas
        textSekolah.text = user.sekolah
        textNis.text = user.nis
        textNisn.text = user.nisn

        update.setOnClickListener {
            showUpdateDialog(user)
        }
        delete.setOnClickListener {
            Deleteinfo(user)
        }
        return view

    }

    private fun Deleteinfo(user: User) {
        val progressDialog = ProgressDialog(
            context,
            com.google.android.material.R.style.Base_V14_Theme_MaterialComponents_Light_Dialog
        )

        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()

        val mydatabase = FirebaseDatabase.getInstance().getReference("PESDIK")
        mydatabase.child(user.id).removeValue()
        Toast.makeText(mCtx, "Deleted!!", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, ShowActivity::class.java)
        context.startActivity(intent)
    }
    private fun showUpdateDialog(user: User) {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.update, null)

        val textNama = view.findViewById<EditText>(R.id.inputNama)
        val textKelas = view.findViewById<EditText>(R.id.inputKelas)
        val textSekolah = view.findViewById<EditText>(R.id.inputSekolah)
        val textNis = view.findViewById<EditText>(R.id.inputnis)
        val textNisn= view.findViewById<EditText>(R.id.inputnisn)

        textNama.setText(user.name)
        textKelas.setText(user.kelas)
        textSekolah.setText(user.sekolah)
        textNis.setText(user.nis)
        textNisn.setText(user.nisn)

        builder.setView(view)
        builder.setPositiveButton("Update"){ dialog, which ->
            val dbUsers = FirebaseDatabase.getInstance().getReference("PESDIK")

            val nama = textNama.text.toString().trim()
            val kelas = textKelas.text.toString().trim()
            val sekolah = textSekolah.text.toString().trim()
            val nis = textNis.text.toString().trim()
            val nisn = textNisn.text.toString().trim()

            if(nama.isEmpty()){
                textNama.error = "Silahkan Masukkan Nama Anda"
                textNama.requestFocus()
                return@setPositiveButton
            }

            if (kelas.isEmpty()){
                textKelas.error = "Silahkan Masukkan Kelas Anda"
                textNama.requestFocus()
                return@setPositiveButton
            }

            if (sekolah.isEmpty()){
                textSekolah.error = "Silahkan Masukkan Sekolah Anda"
                textNama.requestFocus()
                return@setPositiveButton
            }

            if (nis.isEmpty()){
                textNis.error = "Silahkan Masukkan NIS Anda"
                textNama.requestFocus()
                return@setPositiveButton
            }

            if (nisn.isEmpty()){
                textNisn.error = "Silahkan Masukkan NISN Anda"
                textNama.requestFocus()
                return@setPositiveButton
            }

            val user = User(user.id, nama, kelas, sekolah, nis, nisn )

            dbUsers.child(user.id).setValue(user).addOnCompleteListener{
                Toast.makeText(mCtx, "Berhasil Update!", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel"){dialog, which ->

        }
        val alert = builder.create()
        alert.show()
    }
    }



