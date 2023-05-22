package com.yunusemreaydin.sandikapp

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavAction
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirestoreRegistrar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.yunusemreaydin.sandikapp.databinding.FragmentClerkBinding
import com.yunusemreaydin.sandikapp.databinding.FragmentDataBinding
import com.yunusemreaydin.sandikapp.databinding.FragmentMainPageBinding


class dataFragment : Fragment() {
    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!
    val citiesArray = arrayOf<String>("Choose A City...","Adana","Adiyaman","Afyon","Agri","Aksaray","Amasya","Ankara","Antalya","Ardahan","Artvin","Aydin","Balikesir","Bartin","Batman","Bayburt",
        "Bilecik","Bingol","Bitlis","Bolu","Burdur","Bursa","Canakkale","Cankiri","Corum","Denizli","Diyarbakir","Duzce","Edirne","Elazig","Erzincan","Erzurum",
        "Eskisehir","Gaziantep","Giresun","Gumushane","Hakkari","Hatay","Igdir","Isparta","Istanbul","Izmir","Kahramanmaras","Karabuk","Karaman","Kars","Kastamonu",
        "Kayseri","Kilis","Kirikkale","Kirklareli","Kirsehir","Kocaeli","Konya","Kutahya","Malatya","Manisa","Mardin","Mersin","Mugla","Mus","Nevsehir","Nigde","Ordu","Osmaniye","Rize",
        "Sakarya","Samsun","Sanliurfa","Siirt","Sinop","Sirnak","Sivas","Tekirdag","Tokat","Trabzon","Tunceli","Usak","Van","Yalova","Yozgat","Zonguldak")

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage : FirebaseStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        firestore = Firebase.firestore
        storage = Firebase.storage

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        val view = binding.root
        val spinner = binding.citiesAdd
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, citiesArray)
        spinner.adapter = adapter
        val voteRte = binding.voteOfErdogan.text
        val voteMi = binding.voteOfInce.text
        val voteKk = binding.voteOfKilicdaroglu.text
        val voteSo = binding.voteOfOgan.text
        val voteEv = binding.emptyVote.text
        val voteIv = binding.invalidVote.text
        val voteMap = hashMapOf<String, Any?>()



        val addButton = binding.addVote
        addButton.setOnClickListener {
            if(spinner.selectedItem == "Choose A City..."){
                Toast.makeText(requireContext(), "Please Choose A City!", Toast.LENGTH_LONG).show()
            }else if (spinner.selectedItem != "Choose A City..."){
                if (voteSo.isEmpty() || voteEv.isEmpty() || voteIv.isEmpty() || voteKk.isEmpty() || voteRte.isEmpty() || voteMi.isEmpty()){
                    Toast.makeText(requireContext(),"Please Enter All The Votes!!", Toast.LENGTH_LONG).show()
                }else{
                    voteMap.put("Recep Tayyip Erdoğan", binding.voteOfErdogan.text.toString().toInt())
                    voteMap.put("Muharrem İnce", binding.voteOfInce.text.toString().toInt())
                    voteMap.put("Kemal Kılıçdaroğlu", binding.voteOfKilicdaroglu.text.toString().toInt())
                    voteMap.put("Sinan Oğan", binding.voteOfOgan.text.toString().toInt())
                    voteMap.put("Empty Vote", binding.emptyVote.text.toString().toInt())
                    voteMap.put("Invalid Vote", binding.invalidVote.text.toString().toInt())
                    voteMap.put("City", spinner.selectedItem.toString())

                    firestore.collection("votes").add(voteMap).addOnSuccessListener {
                        Toast.makeText(requireContext(), "Votes Are Added Successfully!", Toast.LENGTH_LONG).show()
                        val finalHost = NavHostFragment.create(com.yunusemreaydin.sandikapp.R.navigation.navigation_graph)
                        binding.voteOfErdogan.text = null
                        binding.voteOfInce.text = null
                        binding.voteOfOgan.text = null
                        binding.voteOfKilicdaroglu.text = null
                        binding.invalidVote.text = null
                        binding.emptyVote.text = null


                    }.addOnFailureListener {
                        Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        val showResult = binding.showResults
        showResult.setOnClickListener {
            val action = dataFragmentDirections.actionDataFragmentToResultsFragment()
            Navigation.findNavController(it).navigate(action)
        }



        return view
    }






}