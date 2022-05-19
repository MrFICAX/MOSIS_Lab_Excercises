package elfak.mosis.myplaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.databinding.FragmentSecond2Binding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Second2Fragment : Fragment() {

    private var _binding: FragmentSecond2Binding? = null
    private lateinit var places: ArrayList<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecond2Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        places = ArrayList<String>()
        places.add("Tvrdjava")
        places.add("Trg Kralja Milana")
        places.add("Cair")
        places.add("Park Svetog Save")

        val myPlacesList: ListView = requireView().findViewById<ListView>(R.id.my_places_list)
        myPlacesList.adapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, places)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}