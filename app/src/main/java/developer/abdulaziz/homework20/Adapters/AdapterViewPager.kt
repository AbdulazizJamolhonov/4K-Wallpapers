package developer.abdulaziz.homework20.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson
import developer.abdulaziz.homework20.Class.User
import developer.abdulaziz.homework20.ItemFragment

class AdapterViewPager(
    private var arrayListTypes: ArrayList<String>,
    private var arrayListWallpapers: ArrayList<User>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = arrayListTypes.size

    override fun createFragment(position: Int): Fragment {
        val arrayList = ArrayList<User>()
        return if (position != 0) {
            for (i in arrayListWallpapers) {
                if (i.imageType == arrayListTypes[position]) {
                    arrayList.add(i)
                }
            }
            ItemFragment.newInstance(Gson().toJson(arrayList))
        } else {
            ItemFragment.newInstance(Gson().toJson(arrayListWallpapers))
        }
    }
}