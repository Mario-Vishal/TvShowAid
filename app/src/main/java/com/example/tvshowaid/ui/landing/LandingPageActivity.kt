package com.example.tvshowaid.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.*
import com.example.tvshowaid.R

class LandingPageActivity : AppCompatActivity() {

    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var dynamicActionButton: Button
    private var toggleFragment: Int =0
    private lateinit var dynamicTextView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        fragmentContainer = findViewById(R.id.landing_page_fragment_container)
        dynamicActionButton = findViewById(R.id.dynamicButton)
        dynamicTextView = findViewById(R.id.or)

        openFragment()

        dynamicActionButton.setOnClickListener({

            openFragment()
        })

    }

    private fun openFragment(){

        val fragment:Fragment
        if(toggleFragment==0){
            fragment = SignInFragment()
            dynamicActionButton.text = "Sign Up"
            dynamicTextView.text = "Don't have an account?"
            toggleFragment++
        }
        else{
            fragment = SignUpFragment()

            dynamicActionButton.text = "Sign In"
            dynamicTextView.text = "Have an account?"
            toggleFragment--
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.landing_page_fragment_container,fragment)
        transaction.commit()
    }


}