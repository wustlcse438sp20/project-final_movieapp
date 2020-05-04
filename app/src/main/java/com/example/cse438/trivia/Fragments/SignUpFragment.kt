package com.example.cse438.trivia.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cse438.trivia.R
import com.example.cse438.trivia.data.user
import com.example.cse438.trivia.viewmodel.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.storage.StorageReference
//import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.signupfragment.*


class SignUpFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel

    private val REQUEST_CODE : Int = 1000
//    private lateinit var mStorage : StorageReference
    private var url : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        mStorage = FirebaseStorage.getInstance().getReference("img_uploads")


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.signupfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signup_button.setOnClickListener{
            //create new users
            val email = signup_email.text.toString()
            val password = signup_password.text.toString()
            val username = signup_username.text.toString()
            Toast.makeText(this.context, email, Toast.LENGTH_SHORT).show()

            //edge case check
            if(email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                Toast.makeText(this.context, "Please don't submit empty fields", Toast.LENGTH_SHORT).show()

            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener

                    val userId = it.result!!.user!!.uid

                    val newUser = user( username, email,0, 0,0.0,0, url)

                    val db = FirebaseFirestore.getInstance().collection("users").document(userId)

                    db .set(newUser).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(tag, "add new user to database success.")
                            activity?.finish()
                        } else {
                            Log.e(tag, "create user with email failed.", it.exception)
                            Toast.makeText(this.context, "failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Log.d("In Creating User", "Created User: ${it.result?.user?.uid}$")
                }.addOnFailureListener{
                    Log.d("here", "Failed Creating user: ${it.message}$")

                }
        }

//        btnChoose.setOnClickListener({
//            getImage()
//        })


    }

//    fun getImage() {
//        val intent = Intent()
//        intent.setType("image/*")
//        intent.setAction(Intent.ACTION_GET_CONTENT)
//        startActivityForResult(
//            Intent.createChooser(
//                intent,
//                "Select"),
//            REQUEST_CODE
//        )
//    }

//    //handle result of picked image
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null && data.data != null){
//
//            val task = mStorage!!.putFile(data!!.data!!)
//            val rst = task.continueWithTask{
//                    rst ->
//                if(!rst.isSuccessful) {
//                    Toast.makeText(this.context, "Failed Uploading", Toast.LENGTH_SHORT).show()
//                }
//                mStorage!!.downloadUrl
//            }.addOnCompleteListener{rst->
//                if(rst.isSuccessful) {
//                    val downloadURL = rst.result
//                    val url = downloadURL!!.toString().substring(0,downloadURL.toString().indexOf("&token"))
//                    Picasso.get().load(url).into(image_view)
//                    this.url = url
//                }
//            }
//
//        }
    }
//
