package com.yunusemreaydin.sandikapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yunusemreaydin.sandikapp.databinding.FragmentClerkBinding



class clerkFragment : Fragment() {
    private var _binding: FragmentClerkBinding? = null
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
        _binding = FragmentClerkBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSignIn = binding.clerkSignIn


        buttonSignIn.setOnClickListener {
            val email = binding.clerkMail.text.toString()
            val password = binding.clerkPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Enter Mail and Password!", Toast.LENGTH_LONG).show()

            }else{
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    val uid = auth.currentUser?.uid.toString()

                    if(uid == "FAeONelZ0aUpcbOwPe1KKrPinzl1"){
                        Toast.makeText(requireContext(), "You Signed In Successfully", Toast.LENGTH_LONG).show()
                        val action = clerkFragmentDirections.actionClerkFragment2ToDataFragment()
                        Navigation.findNavController(buttonSignIn).navigate(action)
                    }else{
                        Toast.makeText(requireContext(), "Invalid User", Toast.LENGTH_LONG).show()
                    }


                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}








