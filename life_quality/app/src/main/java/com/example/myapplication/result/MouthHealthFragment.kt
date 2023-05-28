package com.example.myapplication.result

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.weight
import com.example.myapplication.databinding.FragmentEq5dBinding
import com.example.myapplication.databinding.FragmentMouthHealthBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MouthHealthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MouthHealthFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMouthHealthBinding? = null
    private val binding: FragmentMouthHealthBinding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMouthHealthBinding.inflate(inflater, container, false)

        val maxValue = binding.progressbar.max
        val progressValue = weight.toInt()

        binding.progressbar.progress = progressValue
        val startIndex = binding.info.text.indexOf("높을수록")
        val endIndex = binding.info.text.indexOf("높음") + "높음".length
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.red)) // 색깔 지정
        val spannableString = SpannableString(binding.info.text)
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.info.text = spannableString

        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val progressBarWidth = binding.progressbar.width
                val progress = (progressValue.toFloat() / maxValue.toFloat()) * progressBarWidth.toFloat()

                val params = binding.currentPositionImage.layoutParams as RelativeLayout.LayoutParams
                params.marginStart = progress.toInt() - binding.currentPositionImage.width / 2
                binding.currentPositionImage.layoutParams = params
                val textParams = binding.progressText.layoutParams as RelativeLayout.LayoutParams
                textParams.addRule(RelativeLayout.BELOW, R.id.progressbar)
                textParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
                binding.progressText.layoutParams = textParams
                binding.progressText.text = progressValue.toString()+"점"
                // 레이아웃 리스너를 제거합니다.
                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}