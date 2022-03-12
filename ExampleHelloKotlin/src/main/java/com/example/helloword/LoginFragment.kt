package com.example.helloword

import android.accounts.Account
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    companion object {
        private const val TAG = "LoginFragment"
    }

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var statusTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun initView() {
        loginButton.setOnClickListener {
            Log.d(TAG, "initView: ${it.id}")
        }
        val account = Account("name", "type")
        val type = account.type?.trim()
        var name: String? = account.name!!.trim() //!! 运算符将其左侧的所有内容视为非 null
        name = account.name?.trim()
        name = account.name?.trim() ?: "Default name"
    }
}