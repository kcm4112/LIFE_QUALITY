package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginLayoutBinding
import com.example.myapplication.model.User
import com.example.retrofitpractice.Model.Data
import com.example.retrofitpractice.service.ApiService
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: LoginLayoutBinding
    companion object{
        lateinit var user: User
        val Db = Firebase.firestore
        val userCollectionRef = Db.collection("User")
        var phone: String = ""
        var edu_lock : Boolean = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            if(binding.inputPhone.text.toString()==""){
                Toast.makeText(this,"휴대폰번호를 입력해주세요!",Toast.LENGTH_SHORT).show()
            }
            else {
                phone = binding.inputPhone.text.toString()
                Log.d("problem", "휴대폰 번호 : ${phone}")
                val userDocRef = userCollectionRef.document(phone)
                userDocRef.get()
                    .addOnSuccessListener { documentSnapshot ->
                        if(documentSnapshot.exists()){
                            val data = documentSnapshot.data
                            if(data!=null){
                                user = User(
                                    data["sex"].toString(), data["age"].toString(),
                                    data["family_relation"].toString(),
                                    data["scholarship"].toString(),
                                    data["medical_insurance"].toString(),
                                    data["drink"].toString(),
                                    data["smoke"].toString(),
                                    data["phone_num"].toString()
                                )
                                edu_lock=true
                                Log.d("problem", "User : ${user}")
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else {
                                // 문서가 존재하지 않음
                                Toast.makeText(this, "휴대폰 번호를 확인해주세요!", Toast.LENGTH_SHORT).show()
                                Log.d("problem","실패")
                            }
                        }
                        else{
                            Toast.makeText(this, "휴대폰 번호를 확인해주세요!", Toast.LENGTH_SHORT).show()
                            Log.d("problem","실패")
                        }
                    }
                    .addOnFailureListener { e -> Log.e("problem", "실패") }
            }
        }


        binding.goRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity,AgreeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}