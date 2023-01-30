package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.Type6FragmentBinding
import com.example.myapplication.databinding.Type7FragmentBinding

class Fragment7 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.
        var keyList = mutableListOf<String>()
        var valueList = mutableListOf<String>()
        var binding = Type7FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type7Number.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.type7Title.text = QuestionMainpage.tempSurvey.title.toString()

        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }


        binding.type7Answer1.text = valueList.get(0)
        binding.type7Answer2.text = valueList.get(1)
        binding.type7Answer3.text = valueList.get(2)
        binding.type7Answer4.text = valueList.get(3)
        binding.type7Answer5.text = valueList.get(4)
        binding.type7Answer6.text = valueList.get(5)
        binding.type7Answer7.text = valueList.get(6)
        var view = inflater.inflate(R.layout.type7_fragment, container, false)
        return binding.root

    }
}