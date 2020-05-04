package com.example.cse438.trivia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import com.example.cse438.trivia.Fragments.LogInFragment
import com.example.cse438.trivia.Fragments.SignUpFragment
import com.example.cse438.trivia.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(){

        lateinit var firestore: FirebaseFirestore

        private lateinit var viewModel: MainActivityViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


            val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
            viewpager_main.adapter= fragmentAdapter
            tabs_main.setupWithViewPager(viewpager_main)

        }

        public override fun onStart() {
            super.onStart()

            // Start sign in if necessary
            if (shouldStartSignIn()) {
                startSignIn()
                return
            }

//        // Apply filters
//        onFilter(viewModel.filters)

            // Start listening for Firestore updates
//        adapter.startListening()
        }

        private fun shouldStartSignIn(): Boolean {
            return FirebaseAuth.getInstance().currentUser == null
        }

        private fun startSignIn() {
//        val intent = AuthUI.getInstance().createSignInIntentBuilder()
//            .setAvailableProviders((listOf(AuthUI.IdpConfig.EmailBuilder().build())))
//            .setIsSmartLockEnabled(false)
//            .build()
//
//        startActivityForResult(intent, RC_SIGN_IN)
            viewModel.isSigningIn = true
        }


        class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

            override fun getCount() : Int {
                return 2
            }

            override fun getItem(position: Int) : Fragment {
                return when (position) {
                    0 -> { LogInFragment() }
                    else -> SignUpFragment()
                }
            }

            override fun getPageTitle(position: Int): CharSequence {
                return when (position) {
                    0 -> "Log In"
                    else -> "Sign Up"
                }
            }

        }
        private fun showSignInErrorDialog(@StringRes message: Int) {
            val dialog = AlertDialog.Builder(this)
                .setTitle(R.string.title_sign_in_error)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.option_retry) { _, _ -> startSignIn() }
                .setNegativeButton(R.string.option_exit) { _, _ -> finish() }.create()

            dialog.show()
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == RC_SIGN_IN) {
                val response = IdpResponse.fromResultIntent(data)
                viewModel.isSigningIn = false

                if (resultCode != Activity.RESULT_OK) {
                    if (response == null) {
                        // User pressed the back button.
                        finish()
                    } else if (response.error != null && response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                        showSignInErrorDialog(R.string.message_no_network)
                    } else {
                        showSignInErrorDialog(R.string.message_unknown)
                    }
                }
            }
        }

        companion object {

            private const val TAG = "MainActivity"

            private const val RC_SIGN_IN = 9001

            private const val LIMIT = 50
        }
    }


