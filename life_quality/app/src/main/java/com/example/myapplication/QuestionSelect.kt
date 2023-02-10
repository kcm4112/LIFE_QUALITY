package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.example.myapplication.MainActivity.Companion.surveyList
import com.example.myapplication.TotalSurvey
import com.example.myapplication.databinding.ActivityQuestionSelectBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuestionSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding  = ActivityQuestionSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        var tes = mutableListOf<TotalSurvey>()
        var nameList = mutableListOf<String>("EQ5D","Fall", "Frailty", "IPAQ", "MNA", "MouthHealth", "SGDSK", "SleepHabit")
         binding.selectStart.setOnClickListener{ //설문 시작하기 버튼 눌렀을 때
            Toast.makeText(this@QuestionSelect,"설문시작하기 버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            var checkRadio :Int = binding.group.checkedRadioButtonId
             if(checkRadio==-1){
                 //만약 라디오버튼을 선택하지 않은 채 시작하기 버튼을 눌렀다면 진행하면 안됨.
                 Toast.makeText(this,"설문목록을 선택해주세요",Toast.LENGTH_SHORT).show()
             }
            else{
                var btn = binding.group.findViewById<RadioButton>(checkRadio)
                 var dbid : Int = 0
                 when(btn.text.toString()){
                     "삶의 질" -> dbid =0 //EQ5D
                     "수면습관" -> dbid=7 //Sleep
                     "정신건강" ->  dbid=6 //SGDSK
                     "구강건강" -> dbid=5 //Mouth
                     "영앙상태측정(MNA)" -> dbid=4 //MNA
                     "신체활동설문(IPAQ)" -> dbid=3 //IPAQ
                     "낙상" -> dbid=1
                     "노쇠측정" -> dbid=2
                 }
                surveyList.clear()
                 Log.d("test","${btn.text.toString()}"+"${nameList[dbid]}"+"  dbid = " +dbid)
                     db.collection("${nameList[dbid]}")
                         .get()
                         .addOnSuccessListener{ result->
                             for(document in result) {
                                 Log.d("What is name", "${nameList[dbid]}")
                                 surveyList.add(
                                     TotalSurvey(
                                         nameList[dbid], document.id,
                                         document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                         document.data["answer"] as MutableMap<String, String>,false)
                                 )
                             }
                         }
                         .addOnFailureListener{ exception ->
                             Log.w("Get Data Error", exception)
                         }
                         .addOnCompleteListener {
                             var intent = Intent(this@QuestionSelect, QuestionMainpage::class.java)
                             startActivity(intent)
                         }
                 //선택된 버튼 잘 뽑아옴. 버튼 text에 해당하는 내용에 db를 불러와야함. db 불러오고 questionMain으로 넘어가야함.

             }
            //해당 버튼의 해당하는 설문리스트를 불러와야함.
        }
        binding.selectClear.setOnClickListener{ //설문 완료 눌렀을떄
            Toast.makeText(this@QuestionSelect,"설문완료하기 버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            //
        }
    }
}