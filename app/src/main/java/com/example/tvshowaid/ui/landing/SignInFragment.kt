package com.example.tvshowaid.ui.landing

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.tvshowaid.R
import com.example.tvshowaid.databinding.FragmentSignInBinding
import com.example.tvshowaid.ui.home.BaseActivity


class SignInFragment : Fragment() {


    private lateinit var binding:FragmentSignInBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.singinButton.setOnClickListener {

            Toast.makeText(requireContext(), "hehe", Toast.LENGTH_SHORT).show()

            startActivity(
                Intent(requireContext(), BaseActivity::class.java)
            )
        }
    }

    fun openFragment(fragment: Fragment){

        requireParentFragment().
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.landing_page_fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }



}