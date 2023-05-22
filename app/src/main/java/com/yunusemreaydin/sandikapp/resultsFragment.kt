package com.yunusemreaydin.sandikapp

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.yunusemreaydin.sandikapp.databinding.FragmentClerkBinding
import com.yunusemreaydin.sandikapp.databinding.FragmentMainPageBinding
import com.yunusemreaydin.sandikapp.databinding.FragmentResultsBinding
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class resultsFragment : Fragment() {

    private lateinit var spinner: Spinner
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage : FirebaseStorage

    val citiesArray = arrayOf<String>("Please Choose A Location...","Turkey","Adana","Adiyaman","Afyon","Agri","Aksaray","Amasya","Ankara","Antalya","Ardahan","Artvin","Aydin","Balikesir","Bartin","Batman","Bayburt",
        "Bilecik","Bingol","Bitlis","Bolu","Burdur","Bursa","Canakkale","Cankiri","Corum","Denizli","Diyarbakir","Duzce","Edirne","Elazig","Erzincan","Erzurum",
        "Eskisehir","Gaziantep","Giresun","Gumushane","Hakkari","Hatay","Igdir","Isparta","Istanbul","Izmir","Kahramanmaras","Karabuk","Karaman","Kars","Kastamonu",
        "Kayseri","Kilis","Kirikkale","Kirklareli","Kirsehir","Kocaeli","Konya","Kutahya","Malatya","Manisa","Mardin","Mersin","Mugla","Mus","Nevsehir","Nigde","Ordu","Osmaniye","Rize",
        "Sakarya","Samsun","Sanliurfa","Siirt","Sinop","Sirnak","Sivas","Tekirdag","Tokat","Trabzon","Tunceli","Usak","Van","Yalova","Yozgat","Zonguldak")

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
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val view = binding.root
        val spinner = binding.citiesResults
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, citiesArray)
        spinner.adapter = adapter
        val resultButton = binding.resultsButton





        resultButton.setOnClickListener {
            if (spinner.selectedItem == "Please Choose A Location..."){
                Toast.makeText(requireContext(),"Please Choose A Location!", Toast.LENGTH_LONG).show()
            }else {

                val selectedItem = spinner.selectedItem.toString()
                setFragmentResult("city", bundleOf("bundleKey" to selectedItem))
                val action = resultsFragmentDirections.actionResultsFragmentToFinalResultsFragment2()
                Navigation.findNavController(it).navigate(action)
            }
        }


        return view
    }



}
