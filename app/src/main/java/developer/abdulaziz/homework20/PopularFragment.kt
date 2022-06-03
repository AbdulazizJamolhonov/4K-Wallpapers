package developer.abdulaziz.homework20

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulaziz.homework20.Adapters.AdapterViewPager
import developer.abdulaziz.homework20.Class.User
import developer.abdulaziz.homework20.MyShare.MyShare
import developer.abdulaziz.homework20.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var arrayListWallpapers: ArrayList<User>
    private lateinit var arrayListTypes: ArrayList<String>
    private lateinit var adapterViewPager: AdapterViewPager

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPopularBinding.inflate(layoutInflater)
        binding.apply {
            MyShare.init(root.context)

            imageItem.setOnClickListener {
                drawerLayout = requireActivity().findViewById(R.id.drawerLayout)
                drawerLayout.openDrawer(GravityCompat.START)
            }

            createData()
            adapterViewPager =
                AdapterViewPager(arrayListTypes,
                    arrayListWallpapers,
                    parentFragmentManager,
                    lifecycle)
            viewPager.adapter = adapterViewPager

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = arrayListTypes[position]
            }.attach()

            return root
        }
    }

    private fun createData() {
        arrayListWallpapers = ArrayList()
        arrayListTypes = ArrayList()
        arrayListWallpapers.addAll(MyShare.dataList)
        arrayListTypes = buildArrayListTypes()
    }

    private fun buildArrayListTypes(): ArrayList<String> {
        val arrayList = ArrayList<String>()
        val hashSet = HashSet<String>()
        arrayList.add("All")
        for (i in arrayListWallpapers) {
            val boolean = hashSet.add(i.imageType)
            if (boolean) {
                arrayList.add(i.imageType)
            }
        }
        return arrayList
    }

}