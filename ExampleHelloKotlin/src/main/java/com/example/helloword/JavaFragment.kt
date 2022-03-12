package com.example.helloword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * Java类转Kotlin类
 */
internal class JavaFragment : Fragment() {
    private val button: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        button!!.setOnClickListener { v -> Log.d(TAG, "onClick Id :" + v.id) }
    }

    companion object {
        private const val TAG = "JavaFragment"
    }
}