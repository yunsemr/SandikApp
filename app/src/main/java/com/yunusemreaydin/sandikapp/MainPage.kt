package com.yunusemreaydin.sandikapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yunusemreaydin.sandikapp.databinding.FragmentMainPageBinding
import com.yunusemreaydin.sandikapp.databinding.FragmentVoterBinding


class MainPage : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonClerk = binding.goClerk
        val buttonVoter = binding.goVoter
        val currentUser = auth.currentUser

        if (currentUser != null){
            buttonVoter.setOnClickListener {
                val action = MainPageDirections.actionMainPage2ToResultsFragment()
                Navigation.findNavController(it).navigate(action)
            }
            buttonClerk.setOnClickListener {
                val action = MainPageDirections.actionMainPage2ToClerkFragment2()
                Navigation.findNavController(it).navigate(action)
            }
        }else {


            buttonClerk.setOnClickListener {
                val action = MainPageDirections.actionMainPage2ToClerkFragment2()
                Navigation.findNavController(it).navigate(action)
            }

            buttonVoter.setOnClickListener {
                val action2 = MainPageDirections.actionMainPage2ToVoterFragment3()
                Navigation.findNavController(it).navigate(action2)
            }
        }


        

    }

}