package com.example.myapplication.result

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.weight
import com.example.myapplication.databinding.FragmentFallBinding
import com.example.myapplication.databinding.FragmentIpaqBinding
import com.example.myapplication.databinding.FragmentSdohBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IpaqFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class  SdohFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentSdohBinding? = null
    private val binding: FragmentSdohBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSdohBinding.inflate(inflater, container, false)


        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}