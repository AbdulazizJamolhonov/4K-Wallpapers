package developer.abdulaziz.homework20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import developer.abdulaziz.homework20.Adapters.AdapterRecyclerViewWallpapers
import developer.abdulaziz.homework20.Adapters.MyWallpapersOnClickListener
import developer.abdulaziz.homework20.Class.User
import developer.abdulaziz.homework20.databinding.FragmentItemBinding

private const val ARG_PARAM1 = "param1"

class ItemFragment : Fragment() {
    private lateinit var binding: FragmentItemBinding
    private lateinit var arrayListWallpapers: ArrayList<User>
    private lateinit var adapterRecyclerViewWallpapers: AdapterRecyclerViewWallpapers
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentItemBinding.inflate(layoutInflater)
        arrayListWallpapers = ArrayList()
        arrayListWallpapers = gsonToArray(param1.toString())
        adapterRecyclerViewWallpapers = AdapterRecyclerViewWallpapers(binding.root.context,
            arrayListWallpapers,
            requireActivity().findNavController(R.id.fragmentNavigationParent),
            object : MyWallpapersOnClickListener {
                override fun onClick(navController: NavController) {
                    navController.navigate(R.id.action_menuFragment_to_showWallpapersFragment)
                }
            })
        binding.recyclerView.adapter = adapterRecyclerViewWallpapers
        return binding.root
    }

    companion object {
        fun newInstance(param1: String) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    private fun gsonToArray(gsonString: String): ArrayList<User> {
        val list = ArrayList<User>()
        val type = object : TypeToken<List<User>>() {}.type
        list.addAll(Gson().fromJson(gsonString, type))
        return list
    }

}