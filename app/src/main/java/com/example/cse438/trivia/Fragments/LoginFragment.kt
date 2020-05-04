package com.example.cse438.trivia.Fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cse438.trivia.R

import kotlinx.android.synthetic.main.loginfragment.*


import com.google.firebase.auth.FirebaseAuth


class LogInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.loginfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        login_button.setOnClickListener{
            //create new users
            val email = login_email.text.toString()
            val password = login_password.text.toString()
            Toast.makeText(this.context, email, Toast.LENGTH_SHORT).show()

            //edge case check
            if(email.isEmpty() || password.isEmpty() || email == null) {
                Toast.makeText(this.context, "Please don't submit empty fields", Toast.LENGTH_SHORT).show()
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener
                    Log.d("/login", "Logged In User: ${it.result?.user?.uid}$")
                    activity?.finish()
                }.addOnFailureListener{
                    Log.d("/login", "Failed Creating user: ${it.message}$")
                }
        }



    }


}
