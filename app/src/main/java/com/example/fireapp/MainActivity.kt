package com.example.fireapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("PESDIK")

        btn_submit.setOnClickListener{
            saveData()
        }
        btn_lihat.setOnClickListener{
            val intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveData() {


        val nama = edit_name.text.toString()
        val kelas = edit_kelas.text.toString()
        val sekolah = edit_Sekolah.text.toString()
        val nis = edit_nis.text.toString()
        val nisn = edit_nisn.text.toString()


        val pesdik = User(nama,kelas,sekolah,nis,nisn)
        val pesdikId = ref.push().key.toString()

        ref.child(pesdikId).setValue(pesdik).addOnCompleteListener{
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            edit_name.setText("")
            edit_kelas.setText("")
            edit_Sekolah.setText("")
            edit_nis.setText("")
            edit_nisn.setText("")
        }
    }
}