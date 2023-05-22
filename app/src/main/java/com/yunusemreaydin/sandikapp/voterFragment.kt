package com.yunusemreaydin.sandikapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yunusemreaydin.sandikapp.databinding.FragmentVoterBinding


class voterFragment : Fragment() {


    private var _binding: FragmentVoterBinding? = null
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
        _binding = FragmentVoterBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signupbtn = binding.voterSignUp
        val signinbtn = binding.voterSignIn


        signupbtn.setOnClickListener(View.OnClickListener {
            val email = binding.voterMail.text.toString()
            val password = binding.voterPassword.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Enter Mail and Password!", Toast.LENGTH_LONG).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(requireContext(), "You Signed Up Successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }

        })



        signinbtn.setOnClickListener {
            val email = binding.voterMail.text.toString()
            val password = binding.voterPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Enter Mail and Password!", Toast.LENGTH_LONG).show()

            }else{
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(requireContext(), "You Signed In Successfully", Toast.LENGTH_LONG).show()
                    val action = voterFragmentDirections.actionVoterFragment3ToResultsFragment()
                    Navigation.findNavController(signinbtn).navigate(action)

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}


