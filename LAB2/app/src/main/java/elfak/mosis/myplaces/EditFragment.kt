package elfak.mosis.myplaces

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import elfak.mosis.myplaces.data.MyPlace
import elfak.mosis.myplaces.model.MyPlacesViewModel

class EditFragment : Fragment() {

    private val myPlacesViewModel: MyPlacesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_my_places_list -> {
                findNavController().navigate(R.id.action_EditFragment_to_ListFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addButton: Button = requireView().findViewById<Button>(R.id.editmyplace_finished_button)
        addButton.isEnabled = false
        if (myPlacesViewModel.selected != null){
//            findNavController().addOnDestinationChangedListener { controller, destination, arguments ->
//                destination.setLabel(
//                    "Edit My Place"
//                )
//            }
            (activity as AppCompatActivity).supportActionBar?.title = "Edit My Place"
            addButton.setText(R.string.editmyplace_save_label)
        }
        else
            (activity as AppCompatActivity).supportActionBar?.title = "Add New Place"

        val editName: EditText = requireView().findViewById<EditText>(R.id.editmyplace_name_edit)
        editName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                addButton.isEnabled = (editName.text.isNotEmpty())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        val editDesc: EditText = requireView().findViewById<EditText>(R.id.editmyplace_desc_edit)
        if (myPlacesViewModel.selected != null){
            editName.setText(myPlacesViewModel.selected?.name)
            editDesc.setText(myPlacesViewModel.selected?.description)
        }
        addButton.setOnClickListener{
            val name:String = editName.text.toString()
            val desc:String = editDesc.text.toString()
            if (myPlacesViewModel.selected != null){
                myPlacesViewModel.selected?.name = name
                myPlacesViewModel.selected?.description = desc
            }
            else
                myPlacesViewModel.addPlace(MyPlace(name, desc))
            findNavController().navigate(R.id.action_EditFragment_to_ListFragment)
        }
        val cancelButton: Button = requireView().findViewById<Button>(R.id.editmyplace_cancel_button)
        cancelButton.setOnClickListener{
            findNavController().navigate(R.id.action_EditFragment_to_ListFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_new_places)
        item.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myPlacesViewModel.selected = null

    }
}