package com.yunusemreaydin.sandikapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yunusemreaydin.sandikapp.databinding.FragmentFinalResultsBinding
import com.yunusemreaydin.sandikapp.databinding.FragmentResultsBinding


class finalResultsFragment : Fragment() {

    private var _binding: FragmentFinalResultsBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth
    private lateinit var db: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore

    }


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinalResultsBinding.inflate(inflater, container, false)
        val view = binding.root

        val backButton = binding.backButton



        setFragmentResultListener("city",) { city, bundle ->
            var voteOfRte = 0
            var voteOfKk = 0
            var voteOfMi = 0
            var voteOfSo = 0
            var ev = 0
            var iv = 0
            var totalVote = 0
            var totalVoteCounted = 0
            val totalVoter = 60697843
            var rateOfRte : Float
            var rateOfKk : Float
            var rateOfSo : Float
            var rateOfMi : Float
            var countedRes : Float
            val result = bundle.getString("bundleKey")
            binding.cityText.text = result


            if (auth.currentUser != null) {
                if (result != "Turkey") {
                    binding.resGcv.visibility = View.INVISIBLE
                    binding.gcVotes.visibility = View.INVISIBLE
                    db.collection("votes").whereEqualTo("City", result).addSnapshotListener { value, error ->
                        if (error != null) {
                            Toast.makeText(requireContext(),error.localizedMessage, Toast.LENGTH_LONG).show()
                        }else {
                            if (value != null && !value.isEmpty) {
                                val votes = value.documents
                                for (vote in votes) {
                                    val vtRte = vote.get("Recep Tayyip Erdoğan").toString().toInt()
                                    val vtKk = vote.get("Kemal Kılıçdaroğlu").toString().toInt()
                                    val vtMi = vote.get("Muharrem İnce").toString().toInt()
                                    val vtSo = vote.get("Sinan Oğan").toString().toInt()
                                    val vtEv = vote.get("Empty Vote").toString().toInt()
                                    val vtIv = vote.get("Invalid Vote").toString().toInt()
                                    voteOfRte += vtRte
                                    voteOfKk += vtKk
                                    voteOfMi += vtMi
                                    voteOfSo += vtSo
                                    ev += vtEv
                                    iv += vtIv
                                }

                                totalVote = voteOfKk + voteOfMi + voteOfRte + voteOfSo
                                totalVoteCounted = voteOfKk + voteOfMi + voteOfRte + voteOfSo + iv + ev

                                rateOfRte = (voteOfRte.toFloat() / totalVote.toFloat()) * 100
                                rateOfKk = (voteOfKk.toFloat() / totalVote.toFloat()) * 100
                                rateOfMi = (voteOfMi.toFloat() / totalVote.toFloat()) * 100
                                rateOfSo = (voteOfSo.toFloat() / totalVote.toFloat()) * 100
                                countedRes =(totalVoteCounted.toFloat() / totalVoter.toFloat()) * 100
                                rateOfRte = String.format("%.2f",rateOfRte).toFloat()
                                rateOfKk = String.format("%.2f",rateOfKk).toFloat()
                                rateOfMi  = String.format("%.2f",rateOfMi).toFloat()
                                rateOfSo = String.format("%.2f",rateOfSo).toFloat()
                                countedRes = String.format("%.2f",countedRes).toFloat()

                                binding.kkRatio.text = "$rateOfKk%"
                                binding.miRatio.text = "$rateOfMi%"
                                binding.rteRatio.text = "$rateOfRte%"
                                binding.soRatio.text = "$rateOfSo%"
                                binding.kkVts.text = "%,d".format(voteOfKk)
                                binding.rteVts.text = "%,d".format(voteOfRte)
                                binding.soVts.text = "%,d".format(voteOfSo)
                                binding.miVts.text = "%,d".format(voteOfMi)
                                binding.resEv.text = "%,d".format(ev)
                                binding.resIv.text = "%,d".format(iv)


                            }
                        }
                    }
                }else{
                    db.collection("votes").addSnapshotListener { value, error ->
                        if (error != null) {
                            Toast.makeText(requireContext(),error.localizedMessage,Toast.LENGTH_LONG).show()
                        }else {
                            if (value != null && !value.isEmpty) {
                                val votes = value.documents
                                for (vote in votes) {
                                    val vtRte =vote.get("Recep Tayyip Erdoğan").toString().toInt()
                                    val vtKk = vote.get("Kemal Kılıçdaroğlu").toString().toInt()
                                    val vtMi = vote.get("Muharrem İnce").toString().toInt()
                                    val vtSo = vote.get("Sinan Oğan").toString().toInt()
                                    val vtEv = vote.get("Empty Vote").toString().toInt()
                                    val vtIv = vote.get("Invalid Vote").toString().toInt()
                                    voteOfRte += vtRte
                                    voteOfKk += vtKk
                                    voteOfMi += vtMi
                                    voteOfSo += vtSo
                                    ev += vtEv
                                    iv += vtIv
                                }


                                totalVote = voteOfKk + voteOfMi + voteOfRte + voteOfSo
                                totalVoteCounted =voteOfKk + voteOfMi + voteOfRte + voteOfSo + iv + ev

                                rateOfRte = ((voteOfRte.toFloat() / totalVote.toFloat()) * 100)
                                rateOfKk = (voteOfKk.toFloat() / totalVote.toFloat()) * 100
                                rateOfMi = (voteOfMi.toFloat() / totalVote.toFloat()) * 100
                                rateOfSo = (voteOfSo.toFloat() / totalVote.toFloat()) * 100
                                countedRes =(totalVoteCounted.toFloat() / totalVoter.toFloat()) * 100

                                rateOfRte = String.format("%.2f",rateOfRte).toFloat()
                                rateOfKk = String.format("%.2f",rateOfKk).toFloat()
                                rateOfMi  = String.format("%.2f",rateOfMi).toFloat()
                                rateOfSo = String.format("%.2f",rateOfSo).toFloat()
                                countedRes = String.format("%.2f",countedRes).toFloat()

                                binding.kkRatio.text = "$rateOfKk%"
                                binding.miRatio.text = " $rateOfMi%"
                                binding.rteRatio.text = " $rateOfRte%"
                                binding.soRatio.text = " $rateOfSo%"
                                binding.kkVts.text = "%,d".format(voteOfKk)
                                binding.rteVts.text = "%,d".format(voteOfRte)
                                binding.soVts.text = "%,d".format(voteOfSo)
                                binding.miVts.text = "%,d".format(voteOfMi)
                                binding.resEv.text = "%,d".format(ev)
                                binding.resIv.text = "%,d".format(iv)
                                binding.resGcv.text = "$countedRes%"

                            }
                        }
                    }
                }
            }
            backButton.setOnClickListener {

                val action = finalResultsFragmentDirections.actionFinalResultsFragmentToResultsFragment()
                Navigation.findNavController(it).navigate(action)
            }
        }



        return view
    }
}