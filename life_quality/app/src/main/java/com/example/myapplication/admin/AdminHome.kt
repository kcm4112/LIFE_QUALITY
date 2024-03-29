package com.example.myapplication.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.SplashActivity.Companion.Total
import com.example.myapplication.databinding.ActivityAdminHomeBinding

class AdminHome : AppCompatActivity() {

    companion object{

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        var check : Boolean = binding.choice.isSelected
        var editAdapter = EditAdapter(Total, binding)
        setContentView(binding.root)
        //시작했을때 쫘악 보여주기.
        val Homeadapter = AdminHomeAdapter(Total,binding)
        var recyclerView : RecyclerView = binding.questionRecycle
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=Homeadapter
        //recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        var stext : String=""
        binding.searchText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //stext = binding.searchText.text.toString()
                //여기서 어뎁터 만들어야할듯?
                Log.d("test","검색어 입력 : ${p0}")
                editAdapter.filter.filter(p0)
            }
        })

        binding.search.setOnClickListener{
            //Toast.makeText(this,"검색버튼을눌렀습니다",Toast.LENGTH_SHORT).show()
            Log.d("test","검색버튼을눌렀습니다")

            recyclerView.layoutManager=LinearLayoutManager(this)
            recyclerView.adapter=editAdapter
            //recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
            //binding.searchText.text.clear()
        }
        binding.adminHome.setOnClickListener{
            //초기화면으로 돌아가야함.
            Toast.makeText(this,"홈버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java) //홈화면 누르면 mainactivity로 이동.
            startActivity(intent)

        }
        binding.plus.setOnClickListener(){
            //질문추가 화면으로 넘어가야함.
            val intent=Intent(this@AdminHome, AdminPlus::class.java)
            Toast.makeText(this@AdminHome,"+버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        binding.choice.setOnClickListener{
            Toast.makeText(this,"선택버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            binding.choice.isSelected = !binding.choice.isSelected

            if(binding.choice.isSelected){
                //binding.choice.setBackgroundColor()
                binding.trash.visibility= View.VISIBLE
                binding.plus.visibility=View.GONE
                val adapter2 = ChoiceAdapter(Total,binding)
                recyclerView.layoutManager=LinearLayoutManager(this)
                recyclerView.adapter=adapter2
                //recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
            }
            else{ //선택버튼 비활성화
                for(i in 0 .. Total.size-1){
                    Total[i].selected=false
                }
                binding.trash.visibility=View.GONE
                binding.plus.visibility=View.VISIBLE
                val adapter2 = ChoiceAdapter(Total,binding)
                recyclerView.layoutManager=LinearLayoutManager(this)
                recyclerView.adapter=adapter2
               // recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
            }
        }

    }
}