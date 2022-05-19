package elfak.mosis.myplaces

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.data.MyPlace
import elfak.mosis.myplaces.databinding.FragmentListBinding
import elfak.mosis.myplaces.model.MyPlacesViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val myPlacesViewModel: MyPlacesViewModel by activityViewModels()
    //private lateinit var places: ArrayList<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_new_places -> {
                this.findNavController().navigate(R.id.action_ListFragment_to_EditFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_my_places_list)
        item.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        places = ArrayList<String>()
//        places.add("Tvrdjava")
//        places.add("Trg Kralja Milana")
//        places.add("Cair")
//        places.add("Park Svetog Save")

//        //myPlacesViewModel.myPlacesList.add("Tvrdjava")
//        myPlacesViewModel.addPlace("Tvrdjava")
//        //myPlacesViewModel.myPlacesList.add("Cair")
//        myPlacesViewModel.addPlace("Cair")
//        //myPlacesViewModel.myPlacesList.add("ParK Svetog Save")
//        myPlacesViewModel.addPlace("Park Svetog Save")
//        //myPlacesViewModel.myPlacesList.add("Trg Kralja Milana")
//        myPlacesViewModel.addPlace("Trg Kralja Milana")
//        //myPlacesViewModel.myPlacesList.add("Bubanj")
//        myPlacesViewModel.addPlace("Bubanj")
//
//        myPlacesViewModel.addPlace("Gradsko polje")

        val myPlacesList: ListView = requireView().findViewById<ListView>(R.id.my_places_list)
        myPlacesList.adapter = ArrayAdapter<MyPlace>(view.context, android.R.layout.simple_list_item_1, myPlacesViewModel.myPlacesList)

        //places = arrayOf("Tvrdjava", "Cair", "Park Svetog Save", "Trg Kralja Milana")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}